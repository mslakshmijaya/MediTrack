package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.enums.AppointmentStatus;

public class PatientNotificationObserver implements AppointmentObserver {
    @Override
    public void update(Appointment appointment) {
        System.out.println("📢 Notification to Patient "
                + appointment.getPatient().getName()
                + ": Your appointment " + appointment.getId()
                + " has been " + appointment.getStatus() + ".");
    }
}
