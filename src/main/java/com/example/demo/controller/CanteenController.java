package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CanteenService;
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
@RequestMapping("/canteens")
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @PostMapping
    public ResponseEntity<CreatedCanteenDTO> create(@RequestBody CreateCanteenDTO dto, @RequestHeader("studentId") String studentId) {
        CreatedCanteenDTO created = canteenService.create(studentId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCanteenDTO> getById(@PathVariable String id) {
        GetCanteenDTO canteen = canteenService.getById(id);
        return ResponseEntity.ok(canteen);
    }

    @GetMapping
    public ResponseEntity<List<GetCanteenDTO>> getAll() {
        List<GetCanteenDTO> canteens = canteenService.getAll();
        return ResponseEntity.ok(canteens);
    }

    @PutMapping("/{canteenId}")
    public ResponseEntity<UpdatedCanteenDTO> update(@RequestHeader("studentId") String studentId, @PathVariable String canteenId, @RequestBody UpdateCanteenDTO dto) {
        UpdatedCanteenDTO canteen = canteenService.update(studentId, canteenId, dto);
        return ResponseEntity.ok(canteen);
    }

    @DeleteMapping("/{canteenId}")
    public ResponseEntity<Void> delete(
            @RequestHeader("studentId") String studentId,
            @PathVariable String canteenId) {
        canteenService.delete(studentId, canteenId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public List<CanteenCapacityDTO> getCanteensStatus(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam("duration") int durationMinutes
    ) {
        return canteenService.getCanteensStatus(startDate, endDate, startTime, endTime, durationMinutes);
    }

    @GetMapping("/{id}/status")
    public CanteenCapacityDTO getCanteenStatus(
            @PathVariable String id,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam("duration") int durationMinutes
    ) {
        return canteenService.getCanteenStatus(id, startDate, endDate, startTime, endTime, durationMinutes);
    }
}
