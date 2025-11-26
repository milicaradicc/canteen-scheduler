package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CanteenService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CreatedReservationDTO> create(@RequestBody CreateReservationDTO dto) {
        CreatedReservationDTO created = reservationService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> delete(@PathVariable String reservationId, @RequestHeader("studentId") String studentId) {
        reservationService.delete(reservationId, studentId);
        return ResponseEntity.ok().build();
    }
}