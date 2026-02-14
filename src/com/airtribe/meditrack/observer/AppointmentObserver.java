package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.enums.AppointmentStatus;

public interface AppointmentObserver {
    void update(Appointment appointment);
}
