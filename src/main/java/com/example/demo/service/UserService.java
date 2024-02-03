package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    // Дополнительные методы сервиса могут быть добавлены по необходимости

    public List<User> getAllUsers() {
        log.info("Get all users: ");
        return userRepository.findAll().stream().map(userMapper :: toDto).collect(Collectors.toList());
    }

    public User getUserById(int userId) {
        Optional<com.example.demo.entity.User> userOptional = userRepository.findById(userId); // Получаем пользователя из репозитория

        if(userOptional.isPresent()){
            com.example.demo.entity.User userEntity = (com.example.demo.entity.User) userOptional.get();
            log.info("Get user: {}", userMapper.toDto(userEntity));
            return userMapper.toDto(userEntity);
        }else{
            return null;
        }

    }

    public User saveUser(User user) {
        log.info("Saving user: {}", user);
        com.example.demo.entity.User savedUser = userRepository.save(userMapper.toEntity(user));
            return userMapper.toDto(savedUser);
    }

    public User deleteUser(int userId) {
        Optional<com.example.demo.entity.User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            com.example.demo.entity.User userEntity = (com.example.demo.entity.User) userOptional.get();
            log.info("Delete user: {}", userMapper.toDto(userEntity));
            userRepository.deleteById(userId);
            return userMapper.toDto(userEntity);
        }else{
            return null;
        }


    }
}

