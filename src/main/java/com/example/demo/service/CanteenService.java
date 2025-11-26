package com.example.demo.service;

import com.example.demo.dto.CreateCanteenDTO;
import com.example.demo.dto.CreatedCanteenDTO;
import com.example.demo.dto.GetCanteenDTO;
import com.example.demo.dto.WorkingHourDTO;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Canteen;
import com.example.demo.model.WorkingHour;
import com.example.demo.repository.CanteenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CanteenService {

    @Autowired
    private CanteenRepository canteenRepository;

    public CreatedCanteenDTO create(String studentId, CreateCanteenDTO dto) {
        if (dto == null) {
            throw new InvalidInputException("Request body is missing.");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidInputException("Canteen name must not be empty.");
        }
        if (dto.getLocation() == null || dto.getLocation().isBlank()) {
            throw new InvalidInputException("Canteen location must not be empty.");
        }
        if (dto.getCapacity() <= 0) {
            throw new InvalidInputException("Canteen capacity must be greater than 0.");
        }
        if (dto.getWorkingHours() == null || dto.getWorkingHours().isEmpty()) {
            throw new InvalidInputException("Working hours list must not be empty.");
        }

        // Validate each working hour
        for (var wh : dto.getWorkingHours()) {
            if (wh.getMeal() == null) {
                throw new InvalidInputException("Meal type must not be null.");
            }
            if (wh.getFrom() == null || wh.getTo() == null) {
                throw new InvalidInputException("Working hour 'from' and 'to' must not be null.");
            }
        }

        // ===== KREIRANJE MODELA =====
        Canteen canteen = new Canteen();
        canteen.setName(dto.getName());
        canteen.setLocation(dto.getLocation());
        canteen.setCapacity(dto.getCapacity());

        List<WorkingHour> workingHours = dto.getWorkingHours()
                .stream()
                .map(whDto -> {
                    WorkingHour wh = new WorkingHour();
                    wh.setMeal(whDto.getMeal());
                    wh.setFromTime(whDto.getFrom());
                    wh.setToTime(whDto.getTo());
                    wh.setCanteen(canteen);
                    return wh;
                })
                .toList();

        canteen.setWorkingHours(workingHours);

        Canteen saved = canteenRepository.save(canteen);

        CreatedCanteenDTO response = new CreatedCanteenDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setLocation(saved.getLocation());
        response.setCapacity(saved.getCapacity());

        List<WorkingHourDTO> responseHours = saved.getWorkingHours()
                .stream()
                .map(wh -> {
                    WorkingHourDTO whDto = new WorkingHourDTO();
                    whDto.setMeal(wh.getMeal());
                    whDto.setFrom(wh.getFromTime());
                    whDto.setTo(wh.getToTime());
                    return whDto;
                })
                .toList();

        response.setWorkingHours(responseHours);

        return response;
    }

    public GetCanteenDTO getById(int id) {
        Canteen canteen = canteenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Canteen with id " + id + " not found."));

        GetCanteenDTO dto = new GetCanteenDTO();
        dto.setId(canteen.getId());
        dto.setName(canteen.getName());
        dto.setLocation(canteen.getLocation());
        dto.setCapacity(canteen.getCapacity());

        List<WorkingHourDTO> hours = canteen.getWorkingHours()
                .stream()
                .map(wh -> {
                    WorkingHourDTO whDto = new WorkingHourDTO();
                    whDto.setMeal(wh.getMeal());
                    whDto.setFrom(wh.getFromTime());
                    whDto.setTo(wh.getToTime());
                    return whDto;
                })
                .toList();

        dto.setWorkingHours(hours);

        return dto;
    }

    public List<GetCanteenDTO> getAll() {
        return canteenRepository.findAll()
                .stream()
                .map(canteen -> {
                    GetCanteenDTO dto = new GetCanteenDTO();
                    dto.setId(canteen.getId());
                    dto.setName(canteen.getName());
                    dto.setLocation(canteen.getLocation());
                    dto.setCapacity(canteen.getCapacity());

                    List<WorkingHourDTO> hours = canteen.getWorkingHours()
                            .stream()
                            .map(wh -> {
                                WorkingHourDTO whDto = new WorkingHourDTO();
                                whDto.setMeal(wh.getMeal());
                                whDto.setFrom(wh.getFromTime());
                                whDto.setTo(wh.getToTime());
                                return whDto;
                            })
                            .toList();

                    dto.setWorkingHours(hours);

                    return dto;
                })
                .toList();
    }
}
