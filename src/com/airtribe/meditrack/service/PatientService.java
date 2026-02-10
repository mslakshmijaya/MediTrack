package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;

import java.util.*;

public class PatientService {

    private static Map<String, List<Patient>> patientMap = new HashMap<>();

    public static void addPatient(String name, String age, String contactNumber, String gender, String ailment) {
        Patient patient = new Patient( name, age, contactNumber, gender, ailment);
        patientMap
                .computeIfAbsent(patient.getContactNumber(), k -> new ArrayList<>())
                .add(patient);
        System.out.println("Patient added successfully!");

    }

    public static void fetchPatientList() {
        patientMap.forEach((spec, patients) -> {
            patients.forEach(System.out::println);
        });
        }

    // ✅ Search patient by contact number
    public static void searchPatientByContactNumber(String contactNumber) {

        List<Patient> patients = patientMap.get(contactNumber);
        if (patients == null) {
            System.out.println("No patients found for contactNumber: " + contactNumber);
        }
        else {
            patients.forEach(patient -> System.out.println(patient));
        }

    }
    public static void deletePatient(String contactNumber,String patientId) {


            List<Patient> patients = patientMap.get(contactNumber);
            if (patients != null) {
                boolean removed = patients.removeIf(d -> d.getId() .equals(patientId) );
                if (removed) {
                    System.out.println("Patient with ID " + patientId + " removed from " + contactNumber);
                    if (patients.isEmpty()) {
                        patientMap.remove(contactNumber); // clean up empty list
                    }
                } else {
                    System.out.println("Patient with ID " + patientId + " not found in under " + contactNumber);
                }
            } else {
                System.out.println("No Patient registered under the number " + contactNumber);
            }
        }

 public static void updatePatient(String contactNumber,String id,String newName, String newAge, String newAilment)
 {
     List<Patient> patients = patientMap.get(contactNumber);
     if (patients != null) {
         for (Patient patient : patients) {
             if (patient.getId().equals(id)) {
                 // Update details
                 Optional.ofNullable(newName).ifPresent(patient::setName);
                 Optional.ofNullable(newAge).ifPresent(patient::setAge);
                 Optional.ofNullable(newAilment).ifPresent(patient::setAilment);
                 // inherited from Person
                 System.out.println("Patient updated: " + patient);
                 return;
             }
         }
         System.out.println("Patient with ID " + id + " not found in " + contactNumber);
     } else {
         System.out.println("No Patient registered under " + contactNumber);
     }
 }
    // ✅ Search by Name
    public static void searchPatient(String name, boolean isNameSearch) {
        boolean found = false;
        for (List<Patient> patients : patientMap.values()) {
            for (Patient patient : patients) {
                if (patient.getName().equalsIgnoreCase(name)) {
                    System.out.println("Patient found:");
                    System.out.println(patient);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No patient found with name: " + name);
        }
    }

    // ✅ Search by Age
    public static void searchPatient(int age) {
        boolean found = false;
        for (List<Patient> patients : patientMap.values()) {
            for (Patient patient : patients) {
                if (Integer.parseInt(patient.getAge()) == age) {
                    System.out.println("Patient found:");
                    System.out.println(patient);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No patient found with age: " + age);
        }
    }
    public static Patient getPatient(String contactNumber, String patientId) {
        List<Patient> patients = patientMap.get(contactNumber);
        if (patients != null) {
            for (Patient p : patients) {
                if (p.getId().equalsIgnoreCase(patientId)) {
                    return p;
                }
            }
        }
        return null;
    }

}
