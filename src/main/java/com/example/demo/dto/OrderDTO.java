package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;

    // TODO: 18.03.2024 добавь сюда аннотацию notBlank
    private String name;

    // TODO: 18.03.2024 замени тип на обертку Integer и добавь notNull
    private int price;

    // TODO: 18.03.2024 добавь сюда аннотацию notNull
    private Timestamp createDate;

    // TODO: 18.03.2024 Удали этот конструктрор так как он не используется
    public OrderDTO updatedOrder(OrderDTO orderDTO){
        this.name = orderDTO.name;
        this.price = orderDTO.price;
        this.createDate = orderDTO.createDate;
        return this;
    }
}
