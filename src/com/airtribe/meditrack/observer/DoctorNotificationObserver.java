package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.enums.AppointmentStatus;

public class DoctorNotificationObserver implements AppointmentObserver {
    @Override
    public void update(Appointment appointment) {
        System.out.println("📢 Notification to Doctor "
                + appointment.getDoctor().getName()
                + ": Appointment " + appointment.getId()
                + " has been " + appointment.getStatus() + ".");
    }
}