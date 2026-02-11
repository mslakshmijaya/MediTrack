package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Doctor;

import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.enums.AppointmentStatus;

import java.util.*;

public class AppointmentService {

    private static Map<String, Appointment> appointmentMap = new HashMap<>();

    // ✅ Create Appointment
    public static void createAppointment(String contactNumber, String patientId, String doctorId, Specialization specialization, String dateTime) {
        String appointmentId = IdGenerator.generateId(Constants.APPOINTMENT_PREFIX);
        Patient patient = PatientService.getPatient(contactNumber, patientId);
        Doctor doctor = DoctorService.getDoctor(specialization, doctorId);

        Appointment appointment = new Appointment( appointmentId,patient, doctor, dateTime);
        appointmentMap.put(appointmentId, appointment);
        System.out.println("Appointment created successfully: " + appointment);
    }

    // ✅ View Appointments
    public static void viewAppointments() {
        if (appointmentMap.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            appointmentMap.values().forEach(System.out::println);
        }
    }

    // ✅ Cancel Appointment
    public static void cancelAppointment(String appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = appointmentMap.get(appointmentId);
        if (appointment != null) {

            appointment.setStatus(AppointmentStatus.CANCELLED);
            System.out.println("Appointment cancelled: " + appointment);
            return;
        }
        throw new AppointmentNotFoundException("Appointment not found");
    }

    public static Map<String, Appointment> getAllAppointments() {
        return appointmentMap;
    }
}