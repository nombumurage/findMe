package com.example.nombu.findme.model;

/**
 * Created by nombu on 4/13/17.
 */

public class Contacts {
    String uid;
    String eName;
    String phone;

    public Contacts () {}

    public Contacts(String uid, String eName, String phone) {
        this.uid = uid;
        this.eName = eName;
        this.phone = phone;
    }

    public String getUid() { return uid;}

    public String geteName () { return eName;}

    public String getPhone() {return  phone;}

}
