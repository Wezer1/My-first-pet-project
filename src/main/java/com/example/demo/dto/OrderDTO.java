package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Timestamp createDate;
}
