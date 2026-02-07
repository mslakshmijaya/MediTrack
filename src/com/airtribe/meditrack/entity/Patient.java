package com.airtribe.meditrack.entity;

public class Patient extends Person {

    private String ailment;
    public Patient(String prefix, String name, String age, String contactNumber, String gender,String ailment) {
        super(prefix, name, age, contactNumber, gender);
        this.ailment = ailment;
    }

    public String getAilment() {
        return ailment;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }
}
