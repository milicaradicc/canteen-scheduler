package com.example.demo.service;

import com.example.demo.dto.CreateReservationDTO;
import com.example.demo.dto.CreatedReservationDTO;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Canteen;
import com.example.demo.model.Reservation;
import com.example.demo.model.ReservationStatus;
import com.example.demo.model.Student;
import com.example.demo.model.WorkingHour;
import com.example.demo.repository.CanteenRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CanteenRepository canteenRepository;

    @Autowired
    private StudentRepository studentRepository;

    public CreatedReservationDTO create(CreateReservationDTO dto) {
        if (dto.getCanteenId() == null || dto.getStudentId() == null ||
                dto.getDate() == null || dto.getTime() == null || dto.getDuration() <= 0) {
            throw new InvalidInputException("Missing required parameters or duration is invalid.");
        }

        if (dto.getDuration() != 30 && dto.getDuration() != 60) {
            throw new InvalidInputException("Reservation duration must be 30 or 60 minutes.");
        }

        Canteen canteen = canteenRepository.findById(dto.getCanteenId())
                .orElseThrow(() -> new NotFoundException("Canteen with id " + dto.getCanteenId() + " not found."));

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student with id " + dto.getStudentId() + " not found."));

        LocalDateTime reservationStart = LocalDateTime.of(dto.getDate(), dto.getTime());
        LocalDateTime reservationEnd = reservationStart.plusMinutes(dto.getDuration());

        if (reservationStart.isBefore(LocalDateTime.now())) {
            throw new InvalidInputException("Reservation cannot be in the past.");
        }

        if (!isReservationInWorkingHours(canteen, dto.getTime(), dto.getTime().plusMinutes(dto.getDuration()))) {
            throw new InvalidInputException("Reservation time is outside canteen working hours.");
        }

        int currentReservations = reservationRepository.countReservationsInInterval(
                canteen.getNumericId(),
                reservationStart,
                reservationEnd
        );

        if (currentReservations >= canteen.getCapacity()) {
            throw new InvalidInputException("Canteen is fully booked for the requested time slot.");
        }

        Reservation reservation = new Reservation();
        reservation.setStudent(student);
        reservation.setCanteen(canteen);
        reservation.setStartTime(reservationStart);
        reservation.setDurationMinutes(dto.getDuration());
        reservation.setStatus(ReservationStatus.Active);
        reservation.setCreatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);

        CreatedReservationDTO response = new CreatedReservationDTO();
        response.setId(reservation.getId());
        response.setStudentId(student.getId());
        response.setCanteenId(canteen.getId());
        response.setDate(dto.getDate());
        response.setTime(dto.getTime());
        response.setDuration(dto.getDuration());
        response.setStatus(reservation.getStatus());

        return response;
    }

    private boolean isReservationInWorkingHours(Canteen canteen, LocalTime reservationStart, LocalTime reservationEnd) {
        if (canteen.getWorkingHours() == null || canteen.getWorkingHours().isEmpty()) {
            return true;
        }

        for (WorkingHour wh : canteen.getWorkingHours()) {
            try {
                LocalTime whFrom = LocalTime.parse(wh.getFrom());
                LocalTime whTo = LocalTime.parse(wh.getTo());

                if (!reservationStart.isBefore(whFrom) && !reservationEnd.isAfter(whTo)) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public void delete(String reservationId, String studentId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation with id " + reservationId + " not found."));
        if(!reservation.getStudent().getId().equals(studentId)) {
            throw new InvalidInputException("Student with id " + studentId + " not cannot delete this reservation.");
        }
        reservationRepository.delete(reservation);
    }
}