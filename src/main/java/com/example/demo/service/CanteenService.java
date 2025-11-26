package com.example.demo.service;

import com.example.demo.dto.*;
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

        for (var wh : dto.getWorkingHours()) {
            if (wh.getMeal() == null) {
                throw new InvalidInputException("Meal type must not be null.");
            }
            if (wh.getFrom() == null || wh.getTo() == null) {
                throw new InvalidInputException("Working hour 'from' and 'to' must not be null.");
            }
        }

        Canteen canteen = new Canteen();
        canteen.setName(dto.getName());
        canteen.setLocation(dto.getLocation());
        canteen.setCapacity(dto.getCapacity());

        List<WorkingHour> workingHours = dto.getWorkingHours()
                .stream()
                .map(whDto -> {
                    WorkingHour wh = new WorkingHour();
                    wh.setMeal(whDto.getMeal());
                    wh.setFrom(whDto.getFrom());
                    wh.setTo(whDto.getTo());
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
                    whDto.setFrom(wh.getFrom());
                    whDto.setTo(wh.getTo());
                    return whDto;
                })
                .toList();

        response.setWorkingHours(responseHours);

        return response;
    }

    public GetCanteenDTO getById(String id) {
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
                    whDto.setFrom(wh.getFrom());
                    whDto.setTo(wh.getTo());
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
                                whDto.setFrom(wh.getFrom());
                                whDto.setTo(wh.getTo());
                                return whDto;
                            })
                            .toList();

                    dto.setWorkingHours(hours);

                    return dto;
                })
                .toList();
    }

    public UpdatedCanteenDTO update(String studentId, String canteenId, UpdateCanteenDTO updatedCanteenDTO) {
        Canteen canteen = canteenRepository.findById(canteenId)
                .orElseThrow(() -> new NotFoundException("Canteen with id " + canteenId + " not found."));

        canteen.setName(updatedCanteenDTO.getName());
        canteen.setLocation(updatedCanteenDTO.getLocation());
        canteen.setCapacity(updatedCanteenDTO.getCapacity());

        canteen.getWorkingHours().clear();
        if (updatedCanteenDTO.getWorkingHours() != null) {
            List<WorkingHour> workingHours = updatedCanteenDTO.getWorkingHours().stream()
                    .map(dto -> {
                        WorkingHour wh = new WorkingHour();
                        wh.setMeal(dto.getMeal());
                        wh.setFrom(dto.getFrom());
                        wh.setTo(dto.getTo());
                        wh.setCanteen(canteen);
                        return wh;
                    }).toList();
            canteen.getWorkingHours().addAll(workingHours);
        }

        Canteen savedCanteen = canteenRepository.save(canteen);

        UpdatedCanteenDTO response = new UpdatedCanteenDTO();
        response.setId(savedCanteen.getId());
        response.setName(savedCanteen.getName());
        response.setLocation(savedCanteen.getLocation());
        response.setCapacity(savedCanteen.getCapacity());

        if (savedCanteen.getWorkingHours() != null) {
            List<WorkingHourDTO> whDTOs = savedCanteen.getWorkingHours().stream()
                    .map(wh -> {
                        WorkingHourDTO dto = new WorkingHourDTO();
                        dto.setMeal(wh.getMeal());
                        dto.setFrom(wh.getFrom());
                        dto.setTo(wh.getTo());
                        return dto;
                    }).toList();
            response.setWorkingHours(whDTOs);
        }

        return response;
    }

    public void delete(String studentId, String canteenId) {
        Canteen canteen = canteenRepository.findById(canteenId)
                .orElseThrow(() -> new NotFoundException("Canteen with id " + canteenId + " not found."));
        canteenRepository.delete(canteen);
    }
}
