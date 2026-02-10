package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.Specialization;

import java.util.*;

public class DoctorService {
    private static Map<Specialization, List<Doctor>> doctorMap = new HashMap<>();

    public static void addDoctor(String name, String age, String contactNumber, String gender, Specialization specialization,String consultationFee) {
        Doctor doctor = new Doctor( name, age, contactNumber, gender, specialization,consultationFee);
        doctorMap
                .computeIfAbsent(doctor.getSpecialization(), k -> new ArrayList<>())
                .add(doctor);
        System.out.println("Doctor added successfully!");
    }

    public static void searchDoctorBySpecialization(Specialization specialization) {

        List<Doctor> doctors = doctorMap.get(specialization);
        if (doctors == null) {
            System.out.println("No doctors found for specialization: " + specialization);
        }
        else {
            doctors.forEach(doctor -> System.out.println(doctor));
        }


    }
    public static void fetchDoctorList() {
        doctorMap.forEach((spec, docs) -> {
            docs.forEach(System.out::println);
        });
    }

    public static void deleteDoctor(Specialization specialization, String doctorId) {
        List<Doctor> doctors = doctorMap.get(specialization);
        if (doctors != null) {
            boolean removed = doctors.removeIf(d -> d.getId() .equals(doctorId) );
            if (removed) {
                System.out.println("Doctor with ID " + doctorId + " removed from " + specialization);
                if (doctors.isEmpty()) {
                    doctorMap.remove(specialization); // clean up empty list
                }
            } else {
                System.out.println("Doctor with ID " + doctorId + " not found in " + specialization);
            }
        } else {
            System.out.println("No doctors registered under " + specialization);
        }
    }


    public static void updateDoctor(Specialization specialization, String doctorId, String newName, String newAge) {
        List<Doctor> doctors = doctorMap.get(specialization);
        if (doctors != null) {
            for (Doctor doctor : doctors) {
                if (doctor.getId().equals(doctorId)) {
                    // Update details
                    Optional.ofNullable(newName).ifPresent(doctor::setName);
                    Optional.ofNullable(newAge).ifPresent(doctor::setAge);
                    // inherited from Person
                    System.out.println("Doctor updated: " + doctor);
                    return;
                }
            }
            System.out.println("Doctor with ID " + doctorId + " not found in " + specialization);
        } else {
            System.out.println("No doctors registered under " + specialization);
        }
    }
    public static Doctor getDoctor(Specialization specialization, String doctorId) {
        List<Doctor> doctors = doctorMap.get(specialization);
        if (doctors != null) {
            for (Doctor d : doctors) {
                if (d.getId().equalsIgnoreCase(doctorId)) {
                    return d;
                }
            }
        }
        return null;
    }


}
