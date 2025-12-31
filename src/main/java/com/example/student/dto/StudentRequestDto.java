package com.example.student.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDto {

    @NotBlank(message = "Name must not be blank!")
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;
}
