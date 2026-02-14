package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Doctor;

import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.observer.DoctorNotificationObserver;
import com.airtribe.meditrack.observer.PatientNotificationObserver;
import com.airtribe.meditrack.service.billing.BillingStrategy;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.enums.AppointmentStatus;

import java.util.*;

public class AppointmentService {

	private static Map<String, Appointment> appointmentMap = new HashMap<>();

	// ✅ Create Appointment
	public static void createAppointment(String contactNumber, String patientId, String doctorId,
			Specialization specialization, String dateTime) {
		String appointmentId = IdGenerator.generateId(Constants.APPOINTMENT_PREFIX);
		Patient patient = PatientService.getPatient(contactNumber, patientId);
		Doctor doctor = DoctorService.getDoctor(specialization, doctorId);

		Appointment appointment = new Appointment(appointmentId, patient, doctor, dateTime);
		appointmentMap.put(appointmentId, appointment);
        NotifyObservers(appointment);


        System.out.println("Appointment created successfully: " + appointment);
	}
   private static void NotifyObservers(Appointment appointment)
   {
       AppointmentNotifier.addObserver(new PatientNotificationObserver());
       AppointmentNotifier.addObserver(new DoctorNotificationObserver());
       AppointmentNotifier.notifyObservers(appointment);
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
            NotifyObservers(appointment);
			System.out.println("Appointment cancelled: " + appointment);
			return;
		}
		throw new AppointmentNotFoundException("Appointment not found");
	}

	public static Map<String, Appointment> getAllAppointments() {
		return appointmentMap;
	}

	public static Appointment getAppointmentById(String id) throws AppointmentNotFoundException {

		Appointment appointment = appointmentMap.get(id);

		if (appointment != null) {
	        return appointment;
	    }

	   
	    appointment = loadAppointmentFromCSV(id);

	    if (appointment != null) {
	     
	    	appointmentMap.put(id, appointment);
	        return appointment;
	    }

	    // 3️⃣ Not found anywhere
	    throw new AppointmentNotFoundException("Appointment not found");
	}
	
	
	private static Appointment loadAppointmentFromCSV(String id) {

	    List<String> rows = CSVUtil.loadFromCSV("appointments.csv");

	    for (int i = 1; i < rows.size(); i++) { // skip header
	        String[] parts = rows.get(i).split(",");

	        if (parts[0].equals(id)) {

	            String patientId = parts[1];
	            String doctorId = parts[3];
	            String dateTime = parts[5];
	            String status = parts[6];

	            Patient patient = PatientService.searchPatientById(patientId);
	            Doctor doctor = DoctorService.getDoctorById(doctorId);

	            if (patient == null || doctor == null) {
	                return null; // integrity issue
	            }

	            Appointment appointment = new Appointment(
	                    id,
	                    patient,
	                    doctor,
	                    dateTime
	            );

	            appointment.setStatus(AppointmentStatus.valueOf(status));

	            return appointment;
	        }
	    }

	    return null;
	}

	public static Bill generateBill(Appointment appointment, BillingStrategy strategy) {

		double consultationFee = appointment.getDoctor().getConsultationFee();

		return new Bill(appointment.getPatient(), appointment.getDoctor(), appointment, consultationFee, strategy);
	}
	
	
}