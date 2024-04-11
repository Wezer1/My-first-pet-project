package com.example.demo.controller;


import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;


import com.example.demo.dto.UserRegistrationRequestDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return authService.authenticate(authRequestDTO);
    }
}
