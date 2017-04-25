package com.example.nombu.findme.model;

/**
 * Created by nombu on 4/13/17.
 */

public class Insurance {

    String uid;
    String insurance;
    String natId;
    String policyNumber;
    String prefHospital;

    public Insurance(){}

    public Insurance( String uid,String insurance, String natId,  String policyNumber, String prefHospital){
        this.uid=uid;
        this.insurance=insurance;
        this.natId=natId;
        this.policyNumber=policyNumber;
        this.prefHospital=prefHospital;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getNatId() {
        return natId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPrefHospital() {return prefHospital;}

    public String getUid() {
        return uid;
    }

}
