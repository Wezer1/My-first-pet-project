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

    private final AuthService authService; // TODO: 29.03.2024 Ты тут забыл внедрить зависимость. Вместо написания конструктора можно использовать аанотацию  @RequiredArgsConstructor
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO){
        return authService.authenticate(authRequestDTO);
    }

    @PostMapping("/register") // todo Почему у тебя два метода с регистрацией? Второй в UserController. Этот не нущен
    public ResponseEntity<Void> register(@RequestBody UserRegistrationRequestDto registrationRequest) {
        userService.saveUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
