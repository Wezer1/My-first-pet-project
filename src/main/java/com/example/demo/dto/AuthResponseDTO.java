package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.antlr.v4.runtime.Token;

import java.sql.Timestamp;

@Data
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

    public AuthResponseDTO(Integer id, String email, String name, String lastname, Role role, Timestamp birthday, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
        this.birthday = birthday;
        this.token = token;
    }
}
