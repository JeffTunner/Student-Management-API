package com.example.student.controller;

import com.example.student.dto.StudentRequestDto;
import com.example.student.dto.StudentResponseDto;
import com.example.student.repository.StudentRepository;
import com.example.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/add")
    public StudentResponseDto create(@RequestBody @Valid StudentRequestDto studentRequestDto) {
        return service.createStudent(studentRequestDto);
    }

    @GetMapping("/find")
    public StudentResponseDto find(@RequestParam @Valid Long id){
        return service.getById(id);
    }

    @GetMapping("/all")
    public Page<StudentResponseDto> findAll(@RequestParam int page, int size) {
        return service.getStudentsPaginated(page, size);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam @Valid Long id) {
        service.deleteStudent(id);
    }



}
