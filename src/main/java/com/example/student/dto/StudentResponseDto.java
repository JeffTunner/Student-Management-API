package com.example.student.dto;

import com.example.student.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseDto {

    private Long id;
    private String name;
    private int age;
    private Address address;
}
