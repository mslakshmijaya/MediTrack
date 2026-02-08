package com.airtribe.meditrack.entity;

public class Doctor extends Person {
    String specialization;

    public Doctor(String prefix, String name, String age, String contactNumber, String gender, String specialization) {
        super(prefix, name, age, contactNumber, gender);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


}
