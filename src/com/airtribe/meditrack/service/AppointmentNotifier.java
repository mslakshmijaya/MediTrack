package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.observer.AppointmentObserver;
import java.util.ArrayList;
import java.util.List;

public class AppointmentNotifier {
    private static final List<AppointmentObserver> observers = new ArrayList<>();

    public static void addObserver(AppointmentObserver observer) {
        observers.add(observer);
    }

    public static void notifyObservers(Appointment appointment) {
        for (AppointmentObserver observer : observers) {
            observer.update(appointment);
        }
    }
}