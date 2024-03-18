package com.example.demo.dto;


import java.sql.Timestamp;

public class Order {
    private int id;
    private String nameOrder;
    private int priceOrder;
    private java.sql.Timestamp createDate;

    public Order(int id, String nameOrder, int priceOrder, Timestamp createDate) {
        this.id = id;
        this.nameOrder = nameOrder;
        this.priceOrder = priceOrder;
        this.createDate = createDate;
    }
}
