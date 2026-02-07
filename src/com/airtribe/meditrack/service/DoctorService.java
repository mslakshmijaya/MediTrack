package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    public static List<Doctor> doctorList =new ArrayList<Doctor>();

    public static void addDoctor(String name, String age, String contactNumber, String gender,String specialization )
    {
        doctorList.add(new Doctor("DOC",name,age,contactNumber,gender,specialization));
    }
    public static void fetchDoctorList() {
        System.out.println(" Doctor list ");
        for (Doctor doc : doctorList) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(doc.getId()).append("     ")
                    .append(doc.getName()).append("            ")
                    .append(doc.getAge()).append("          ")
                    .append(doc.getSpecialization());

            System.out.println(buffer.toString());

        }
        System.out.println("No Doctor found");

    }
}
