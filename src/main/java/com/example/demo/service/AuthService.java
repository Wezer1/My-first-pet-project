package com.example.demo.service;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service // TODO: 29.03.2024 Ты забыл пометить сервис аннотацией  @Service, из-за этого он не внедрялся как зависимость в контроллер
public class AuthService {

    private  AuthenticationManager authenticationManager;
    private  JwtTokenProvider jwtTokenProvider;
    private  UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));//проводим аунтицикацию через email и пароль
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));//если аунтификация успешна, с помощью email ищем пользователя
            String token = jwtTokenProvider.createToken(user);//если пользователь есть, то создаем токен
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getLastname(), user.getRole(),user.getBirthday(),token);
            return ResponseEntity.ok(authResponseDTO);//передаем пользователя

    }
}
