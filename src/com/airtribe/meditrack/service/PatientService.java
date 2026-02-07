package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    public static List<Patient> patientList =new ArrayList<Patient>();

    public static void addPatient(String name, String age, String contactNumber, String gender,String ailment )
    {
        patientList.add(new Patient("PAT",name,age,contactNumber,gender,ailment));
    }

    public static void fetchPatientList() {
        System.out.println(" Patient list ");
        for (Patient patient : patientList) {
            String buffer = patient.getId() + "     " +
                    patient.getName() + "            " +
                    patient.getAge() + "          " +
                    patient.getAilment();

            System.out.println(buffer);

        }
        System.out.println("No Doctor found");

    }

}
