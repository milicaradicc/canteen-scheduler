package com.example.demo.controller;

import com.example.demo.dto.CreateStudentDTO;
import com.example.demo.dto.CreatedStudentDTO;
import com.example.demo.dto.GetStudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<CreatedStudentDTO> create(@RequestBody CreateStudentDTO dto) {
        CreatedStudentDTO s = studentService.create(dto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetStudentDTO> get(@PathVariable String id) {
        GetStudentDTO s = studentService.get(id);
        return ResponseEntity.ok(s);
    }
}
