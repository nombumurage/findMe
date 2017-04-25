package com.example.nombu.findme.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nombu on 4/13/17.
 */

public class Conditions {
    String uid;
    String bloodGroup;
    String rhesus;
    String otherConditions;

    public Conditions() {}

    public Conditions(String uid, String bloodGroup, String rhesus,
                      String otherConditions){

        this.uid = uid;
        this.bloodGroup = bloodGroup;
        this.rhesus = rhesus;
        this.otherConditions = otherConditions;
    }
    public String getBloodGroup() {return bloodGroup;}

    public String getRhesus() {return rhesus;}

    public String getOtherConditions() {return otherConditions;}

    public String getUid() { return uid;}
}
