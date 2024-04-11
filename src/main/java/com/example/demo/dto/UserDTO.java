package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

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
}
