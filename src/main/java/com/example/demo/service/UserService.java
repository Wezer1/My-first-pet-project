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

    @Transactional
    public List<UserRegistrationResponseDto> getAllUsers() {
        log.info("Get all Users");
        if(userRepository.findAll().isEmpty()){
            throw new NoSuchException("No users");
        }
        return userRepository.findAll().stream().map(userRegisterResponseMapper :: toRegistrationResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public UserRegistrationResponseDto getUserById(Integer userId) {
        log.info("Get user by id: {} ", userId);
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchException("There is no user with ID = "+ userId + " in Database")));
        return userRegisterResponseMapper.toRegistrationResponseDto(userOptional.get());
    }

    @Transactional
    public UserRegistrationResponseDto saveUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        log.info("Saving user: {}", userRegistrationRequestDto);
        User saveUser = userRepository.save(userRegisterRequestMapper.toEntity(userRegistrationRequestDto));
        return userRegisterResponseMapper.toRegistrationResponseDto(saveUser);

    }

    @Transactional
    public UserRegistrationResponseDto changeUser(Integer userId,UserRegistrationRequestDto userRegistrationRequestDto){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new NoSuchException("There is no user with ID = "+ userId + " in Database");
        }else{
            User existingUser = optionalUser.get();
            User updatedUser = userRegisterRequestMapper.toEntity(userRegistrationRequestDto);
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setName(updatedUser.getName());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setBirthday(updatedUser.getBirthday());

            User savedUser = userRepository.save(existingUser);

            return userRegisterResponseMapper.toRegistrationResponseDto(savedUser);
        }
    }

    @Transactional
    public void deleteUser(Integer userId) {
        log.info("Delete user");
        if(userRepository.findById(userId).isEmpty()){
            throw new NoSuchException("There is no user with ID = "+ userId + " in Database");
        }
        userRepository.deleteById(userId);
    }
}
