package com.example.demo.service;


import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRegistrationRequestDto;
import com.example.demo.dto.UserRegistrationResponseDto;
import com.example.demo.entity.User;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRegisterRequestMapper;
import com.example.demo.mapper.UserRegisterResponseMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterRequestMapper userRegisterRequestMapper;
    private final UserRegisterResponseMapper userRegisterResponseMapper;

//    public List<UserDTO> getAllUsers() {
//        log.info("Get all Users");
//        if(userRepository.findAll().isEmpty()){
//            throw new NoSuchException("No users");
//        }
//        return userRepository.findAll().stream().map(userMapper :: toDto).collect(Collectors.toList());
//    }
//
//    public UserDTO getUserById(Integer userId) {
//        log.info("Get user by id: {} ", userId);
//        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userId)
//                .orElseThrow(() -> new NoSuchException("There is no user with ID = "+ userId + " in Database")));
//        return userMapper.toDto(userOptional.get());
//    }
// TODO: 03.04.2024 забыл аанотацию Transactional
    @Transactional
    public UserRegistrationResponseDto saveUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        log.info("Saving user: {}", userRegistrationRequestDto);
        User saveUser = userRepository.save(userRegisterRequestMapper.toEntity(userRegistrationRequestDto));
        return userRegisterResponseMapper.toRegistrationResponseDto(saveUser);
    }

//    public void deleteUser(Integer userId) {
//        log.info("Delete user");
//        if(userRepository.findById(userId).isEmpty()){
//            throw new NoSuchException("There is no user with ID = "+ userId + " in Database");
//        }
//        userRepository.deleteById(userId);
//    }
}
