package com.example.demo.dto;

public class User {

    static private int countOfUser = 1;
    private int id;
    private String name;
    private String surname;

    public User() {
        this.id = countOfUser++;
    }

    public void setId(int id){
        this.id = countOfUser++;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
