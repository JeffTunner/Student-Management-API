package com.example.student.controller;

import com.example.student.dto.StudentRequestDto;
import com.example.student.dto.StudentResponseDto;
import com.example.student.security.JwtUtil;
import com.example.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @Autowired
    JwtUtil jwtUtil;

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

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam @Valid Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDto> update(@PathVariable @Valid Long id, @RequestBody StudentRequestDto requestDto) {
        return ResponseEntity.ok(service.updateStudent(id, requestDto));
    }

    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/{username}/token")
    public String token(@PathVariable String username, @RequestParam(defaultValue = "ROLE_USER") String role) {
        return jwtUtil.generateToken(username, role);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Admin access granted!";
    }

}
