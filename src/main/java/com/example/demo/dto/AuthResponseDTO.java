package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.antlr.v4.runtime.Token;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private Integer id;

    @NotBlank
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    private Role role;

    private Timestamp birthday;

    private String token;
}
