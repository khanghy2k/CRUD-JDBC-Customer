package com.ra.web.Session6.model;

import java.sql.Timestamp;

public class Customer {
    private String id;
    private String name;
    private int age;
    private Timestamp birthday;
    private String avatar;

    public Customer() {
    }

    public Customer(String id, String name, int age, Timestamp birthday, String avatar) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
