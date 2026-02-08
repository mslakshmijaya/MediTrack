package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;

import java.util.*;

public class DoctorService {
    public static List<Doctor> doctorList =new ArrayList<Doctor>();
    private static Map<String, Doctor> doctorMap = new HashMap<>();
    public static void addDoctor(String name, String age, String contactNumber, String gender,String specialization )
    {
        Doctor doctor = new Doctor("DOC", name, age, contactNumber, gender, specialization);
        doctorMap.put(specialization, doctor);
        System.out.println("Doctor added successfully!");
    }
    public static void fetchDoctorList() {

        if (doctorMap.isEmpty()) {
            System.out.println("No Doctor found.");
        } else {
            System.out.println("----- Doctor List  -----");
            Iterator<Doctor> iterator = doctorMap.values().iterator();
            while (iterator.hasNext()) {
                Doctor doctor = iterator.next();
                String buffer = doctor.getId() + "     " + doctor.getName() + "            " + doctor.getAge()+ "            " + doctor.getContactNumber() + "          " + doctor.getSpecialization();

                System.out.println(buffer);
                System.out.println("------------------------");
            }

        }

    }

    public static void searchDoctorBySpecialization(String specialization) {
        boolean found = false;
        for (Doctor doctor : doctorMap.values()) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                System.out.println("Doctor found:");
                String buffer = doctor.getId() + "     " + doctor.getName() + "     " + doctor.getAge()
                        + "     " + doctor.getContactNumber() + "     " + doctor.getSpecialization();
                System.out.println(buffer);
                System.out.println("------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No doctor found with specialization: " + specialization);
        }
    }

}
