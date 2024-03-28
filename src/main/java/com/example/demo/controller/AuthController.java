package com.example.demo.controller;


import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;


import com.example.demo.dto.UserRegistrationRequestDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthService authService;
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO){
        return authService.authenticate(authRequestDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationRequestDto registrationRequest) {
        userService.saveUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
