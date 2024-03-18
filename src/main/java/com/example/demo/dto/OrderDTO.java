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
    private String name;
    private int price;
    private Timestamp createDate;

    public OrderDTO updatedOrder(OrderDTO orderDTO){
        this.name = orderDTO.name;
        this.price = orderDTO.price;
        this.createDate = orderDTO.createDate;
        return this;
    }
}
