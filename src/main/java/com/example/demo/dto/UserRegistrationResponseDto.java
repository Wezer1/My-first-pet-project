package com.example.demo.dto;

import com.example.demo.model.Role;
import com.example.demo.security.JwtTokenProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRegistrationResponseDto {

    private Integer id;

    @NotBlank
    private String email;

    private String password;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    private Role role;

    private Timestamp birthday;



}
