package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.enums.Specialization;

public class Doctor extends Person implements Payable {
    Specialization  specialization;
    private double consultationFee;

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public Doctor( String name, String age, String contactNumber, String gender, Specialization specialization, String consultationFee) {
        super(Constants.DOCTOR_PREFIX, name, age, contactNumber, gender);
        this.specialization = specialization;
        this.consultationFee = Double.parseDouble(consultationFee);
    }

    public Specialization  getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization  specialization) {
        this.specialization = specialization;
    }
    @Override
    public String toString() {
        return super.toString() + ", " + specialization + ", " + consultationFee;
    }
    // ✅ Implement Payable
    @Override
    public double calculatePayment() {
        return consultationFee;
    }


}
