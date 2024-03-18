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
    private int id;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "create_date")
    private Timestamp createDate; // TODO: 18.03.2024 Перенеси путь до Timestamp в импорт

}
