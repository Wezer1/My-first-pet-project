package com.example.demo.mapper;

import com.example.demo.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "createdate", target = "createDate")
    public abstract Order toEntity(com.example.demo.dto.Order order);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "createDate", target = "createdate")
    public abstract com.example.demo.dto.Order toDto(Order order);
}
