package com.example.nombu.findme.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nombu on 4/13/17.
 */

public class Medication {
    String uid;
    String medication;

    public Medication() {}

    public Medication(String uid, String medication) {
        this.uid = uid;
        this.medication = medication;
    }


    public String getMedication() {
        return medication;
    }

    public String getUid() {
        return uid;
    }

}
