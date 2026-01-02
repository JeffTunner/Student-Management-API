package com.example.student.controller;

import com.example.student.dto.StudentRequestDto;
import com.example.student.dto.StudentResponseDto;
import com.example.student.repository.StudentRepository;
import com.example.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/add")
    public ResponseEntity<StudentResponseDto> create(@RequestBody @Valid StudentRequestDto studentRequestDto) {
        StudentResponseDto dto = service.createStudent(studentRequestDto);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/find")
    public ResponseEntity<StudentResponseDto> find(@RequestParam @Valid Long id){
        StudentResponseDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<StudentResponseDto>> findAll(@RequestParam int page, int size) {
        Page<StudentResponseDto> page1 = service.getStudentsPaginated(page, size);
        return ResponseEntity.ok(page1);
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam @Valid Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }



}
