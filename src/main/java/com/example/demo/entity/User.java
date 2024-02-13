package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", length = 64)
    @NotBlank
    private String firstName;

    @Column(name = "second_name", length = 64)
    @NotBlank
    private String secondName;

    @Column(name = "email", length = 64)
    @Email
    @NotBlank
    private String email;

    @Column(name = "birth_date")
    @NotNull
    private java.sql.Timestamp birthDate;



}
