package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRegistrationRequestDto {

    private Integer id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    private Role role;

    private Timestamp birthday;

    public UserRegistrationRequestDto(String email, String password, String name, String lastname, Role role, Timestamp birthday) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
        this.birthday = birthday;
    }
}
