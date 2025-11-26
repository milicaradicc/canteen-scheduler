package com.example.demo.service;

import com.example.demo.dto.CreateStudentDTO;
import com.example.demo.dto.CreatedStudentDTO;
import com.example.demo.dto.GetStudentDTO;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public CreatedStudentDTO create(CreateStudentDTO dto) {
        if (dto.getName() == null || dto.getEmail() == null) {
            throw new InvalidInputException("Name and email are required");
        }

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAdmin(dto.isAdmin());

        studentRepository.save(student);

        CreatedStudentDTO response = new CreatedStudentDTO();
        response.setId(student.getId());
        response.setAdmin(student.isAdmin());
        response.setName(student.getName());
        response.setEmail(student.getEmail());

        return response;
    }

    public GetStudentDTO get(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));

        GetStudentDTO dto = new GetStudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAdmin(student.isAdmin());

        return dto;
    }
}
