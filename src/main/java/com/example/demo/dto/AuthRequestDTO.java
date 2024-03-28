package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;

    public AuthRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
