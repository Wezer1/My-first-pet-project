package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;

    // TODO: 03.04.2024 Используй lombok @AllArgsConstructor вместо написания конструктора 
    public AuthRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
