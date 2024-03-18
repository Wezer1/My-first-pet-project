package com.example.demo.mapper;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    // TODO: 18.03.2024 можно убрать аннотации @Mapping так как у тебя названия и типы полей совпадают, так код будет выглядеть чище
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "createDate", target = "createDate")
    public abstract Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "createDate", target = "createDate")
    public abstract OrderDTO toDto(Order order);
}
