package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // TODO: 19.03.2024 Здесь тоже поменяй тип на Integer

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "create_date")
    private Timestamp createDate;

}
