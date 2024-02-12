package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(source = "name", target = "firstName")
    @Mapping(source = "surname", target = "secondName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "birthDate", target = "birthDate")
    public abstract User toEntity(com.example.demo.dto.User user);

    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "secondName", target = "surname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "birthDate", target = "birthDate")
    public abstract com.example.demo.dto.User toDto(User userEntity);
}

