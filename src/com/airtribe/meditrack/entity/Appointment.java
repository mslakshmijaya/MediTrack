package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;
import java.io.Serializable;

public class Appointment implements Serializable, Cloneable {
    private final String id;
    private Patient patient;
    private Doctor doctor;
    private String dateTime;
    private AppointmentStatus status;

    public Appointment(String id,Patient patient, Doctor doctor,String datetime) {
       this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = datetime;
        this.status = AppointmentStatus.CONFIRMED;
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    @Override
    public Appointment clone() throws CloneNotSupportedException {
        Appointment cloned = (Appointment) super.clone();
      //  cloned.patient = patient.clone(); // deep copy
      //  cloned.doctor = doctor.clone();   // deep copy
        return cloned;
    }

    @Override
    public String toString() {
        return id + " | " + patient.getName() + " with Doctor " + doctor.getName() + " [" + status + "]";
    }
}