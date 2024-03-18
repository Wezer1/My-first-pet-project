package com.example.demo.entity;

// TODO: 18.03.2024 Удали этот класс, позже создадим новый
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "orders")
//@Data
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "name", length = 64)
//    @NotBlank
//    private String name;
//
//    @Column(name = "price")
//    private int price;
//
//    @Column(name = "create_date")
//    @NotNull
//    private java.sql.Timestamp createDate;
//
//
//
//}
