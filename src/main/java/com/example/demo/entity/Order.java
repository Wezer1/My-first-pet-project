package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 64)
    @NotBlank // TODO: 18.03.2024 Здесь эта аннотация избыточна
    private String name;

    @Column(name = "price")
    private int price; // TODO: 18.03.2024 замени тип на обертку Integer

    @Column(name = "create_date")
    @NotNull // TODO: 18.03.2024 Здесь эта аннотация избыточна
    private java.sql.Timestamp createDate; // TODO: 18.03.2024 Перенеси путь до Timestamp в импорт

}
