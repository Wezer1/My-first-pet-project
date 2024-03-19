package com.example.demo.mapper;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract Order toEntity(OrderDTO orderDTO);

    public abstract OrderDTO toDto(Order order);
}
