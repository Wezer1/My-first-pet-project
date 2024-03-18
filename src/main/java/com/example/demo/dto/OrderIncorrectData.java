package com.example.demo.dto;

// TODO: 18.03.2024 Добавь сюда аннотацию @Data
public class OrderIncorrectData {
    private String info;

    // TODO: 18.03.2024 Этот конструктор можешь удалить, так как конструктор без параметров java создаст сама
    public OrderIncorrectData() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
