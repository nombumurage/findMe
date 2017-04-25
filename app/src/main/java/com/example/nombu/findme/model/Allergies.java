package com.example.nombu.findme.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nombu on 4/13/17.
 */

public class Allergies {
    String uid;
    String allergies;

    public Allergies() {}

    public Allergies(String uid, String allergies){
        this.allergies = allergies;
        this.uid = uid;
    }

    public String getAllergies() {return allergies;}

    public String getUid() {
        return uid;
    }
}
