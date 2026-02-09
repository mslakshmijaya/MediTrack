package com.airtribe.meditrack.entity;

import enums.Specialization;

public class Doctor extends Person {
    Specialization  specialization;

    public Doctor(String prefix, String name, String age, String contactNumber, String gender, Specialization specialization) {
        super(prefix, name, age, contactNumber, gender);
        this.specialization = specialization;
    }

    public Specialization  getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization  specialization) {
        this.specialization = specialization;
    }
    @Override
    public String toString() {
        return super.toString() + ", " + specialization;
    }


}
