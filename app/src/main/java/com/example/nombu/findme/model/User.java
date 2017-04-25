package com.example.nombu.findme.model;

/**
 * Created by nombu on 4/13/17.
 */

public class User {
    String name;
    String age;
    String gender;
    String uid;


    public User() {}

    public User(String uid, String name, String age, String gender) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {return name;}

    public String getAge() {return age;}

    public String getGender() {return gender;}

    public String getUid() {
        return uid;
    }
}
