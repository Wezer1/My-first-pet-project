package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getAllUsers() {
        log.info("Get all users: ");
        return userRepository.findAll().stream().map(userMapper :: toDto).collect(Collectors.toList());
    }

    public User getUserById(int userId) {
        log.info("Get user by id: ");
        Optional<com.example.demo.entity.User> userOptional = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь с " + userId + " не найден")));
        return userMapper.toDto(userOptional.get());

    }

    public User saveUser(User user) {
        log.info("Saving user: {}", user);
        com.example.demo.entity.User savedUser = userRepository.save(userMapper.toEntity(user));
            return userMapper.toDto(savedUser);
    }

    public void deleteUser(int userId) {
        log.info("Delete user");
        userRepository.deleteById(userId);
    }
}

