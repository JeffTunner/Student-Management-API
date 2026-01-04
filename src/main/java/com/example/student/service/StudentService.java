package com.example.student.service;

import com.example.student.dto.StudentRequestDto;
import com.example.student.dto.StudentResponseDto;
import com.example.student.entity.Student;
import com.example.student.exception.StudentNotFoundException;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    // DTO -> Entity
    private Student toEntity(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setAddress(studentRequestDto.getAddress());
        return student;
    }

    // Entity -> DTO
    private StudentResponseDto toResponseDto(Student student) {
        return new StudentResponseDto(student.getId(), student.getName(), student.getAge(), student.getAddress());
    }

    public StudentResponseDto createStudent(StudentRequestDto requestDto) {
        Student student = toEntity(requestDto);
        Student saved = studentRepository.save(student);
        return toResponseDto(saved);
    }

    public StudentResponseDto getById(Long id) {
        Student found = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with ID: "+id+" not found!"));
        return toResponseDto(found);
    }

    public Page<StudentResponseDto> getStudentsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(this::toResponseDto);
    }

    public void deleteStudent(Long id) {
        Student found = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with ID: "+id+" not found!"));
        studentRepository.delete(found);
    }
}
