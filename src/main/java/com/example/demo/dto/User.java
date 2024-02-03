package com.example.demo.dto;


import java.sql.Timestamp;

public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private java.sql.Timestamp birthDate;



    public User(int id, String name, String surname, String email, Timestamp birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public void setId(int id){ this.id = id; }

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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public User updatedUser(User user){
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.birthDate = user.birthDate;
        return this;
    }

}
