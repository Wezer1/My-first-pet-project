package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderIncorrectData {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
