package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;

import java.util.*;

public class PatientService {

    private static Map<String, Patient> patientMap = new HashMap<>();

    public static void addPatient(String name, String age, String contactNumber, String gender, String ailment) {
        Patient patient = new Patient("PAT", name, age, contactNumber, gender, ailment);
        patientMap.put(contactNumber, patient);
        System.out.println("Patient added successfully!");

    }

    public static void fetchPatientList() {
        if (patientMap.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("----- Patient List  -----");
            Iterator<Patient> iterator = patientMap.values().iterator();
            while (iterator.hasNext()) {
                Patient patient = iterator.next();
                String buffer = patient.getId() + "     " + patient.getName() + "            " + patient.getAge()+ "            " + patient.getContactNumber() + "          " + patient.getAilment();

                System.out.println(buffer);
                System.out.println("------------------------");
            }

        }
    }
    // ✅ Search patient by contact number
    public static void searchPatientByContactNumber(String contactNumber) {
        Patient patient = patientMap.get(contactNumber);
        if (patient != null) {
            System.out.println("Patient found:");
            String buffer = patient.getId() + "     " + patient.getName() + "            " + patient.getAge()+ "            " + patient.getContactNumber() + "          " + patient.getAilment();
            System.out.println(buffer);
        } else {
            System.out.println("No patient found with contact number: " + contactNumber);
        }
    }

}
