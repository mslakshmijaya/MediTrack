import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidInputException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.Validator;
import com.airtribe.meditrack.enums.Specialization;

import java.util.Scanner;

public class Main {
    static void displayMenu() {
        System.out.println("----- MENU -------");
        System.out.println(":::::::  Clinic & Appointment Management System  :::::::-");
        System.out.println("----- 1.Add a Patient -------");
        System.out.println("----- 2.View all Patient details -------");
        System.out.println("----- 3.Update Patient by Contact Number -------");
        System.out.println("----- 4.Delete a Patient -------");
        System.out.println("----- 5.Search a Patient -------");
        System.out.println("----- 6.Add a Doctor -------");
        System.out.println("----- 7.View all Doctor details -------");
        System.out.println("----- 8.Update a Doctor  details-------");
        System.out.println("----- 9.Delete a Doctor -------");
        System.out.println("----- 10.Search a Doctor -------");
        System.out.println("----- 11.Add Appointment -------");
        System.out.println("----- 12.View all Appointment details -------");
        System.out.println("----- 13. Cancel Appointment -------");
        System.out.println("----- 14. Exit the Menu  -------");
        System.out.print("Enter your choice: ");
    }

    private static void updatePatient() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Contact Number to update: ");
        String contact = input.nextLine();
        System.out.print("Enter Patient ID : ");
        String id = input.nextLine();
        System.out.print("Enter new Name (or press Enter to skip): ");
        String newName = input.nextLine();
        System.out.print("Enter new Age (or press Enter to skip): ");
        String newAge = input.nextLine();

        System.out.print("Enter new Ailment (or press Enter to skip): ");
        String newAilment = input.nextLine();

        PatientService.updatePatient(contact, id,
                newName.isEmpty() ? null : newName,
                newAge.isEmpty() ? null : newAge,
                newAilment.isEmpty() ? null : newAilment);


    }

    private static void updateDoctor() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter specialization to update: ");

        Specialization specialization = getSpecialization();
        System.out.print("Enter DOC ID : ");
        String id = input.nextLine();
        System.out.print("Enter new Name (or press Enter to skip): ");
        String newName = input.nextLine();
        System.out.print("Enter new Age (or press Enter to skip): ");
        String newAge = input.nextLine();

        if (!id.isEmpty()) {
            DoctorService.updateDoctor(specialization,
                    id,
                    newName.isEmpty() ? null : newName,
                    newAge.isEmpty() ? null : newAge
            );
        } else {
            System.out.println("ID value should not be empty");
        }

    }

    private static Specialization getSpecialization() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Specialization:");
        int i = 1;
        for (Specialization spec : Specialization.values()) {
            System.out.println(i + ". " + spec);
            i++;
        }
        System.out.print("Enter choice: ");
        int specChoice = input.nextInt();
        input.nextLine();

        // Map user choice to enum
        Specialization specialization = Specialization.values()[specChoice - 1];
        return specialization;
    }

    private static void addPersonDetails(boolean isPatient) {
        System.out.println("-----Please enter  Person details  -------");
        Scanner input = new Scanner(System.in);
        System.out.println(" Name: ");
        String name = input.nextLine().trim();
        System.out.println(" Age: ");
        String age = input.nextLine().trim();
        System.out.println(" Contact Number : ");
        String contactNumber = input.nextLine().trim();
        boolean isValidPhone = Validator.isValidPhoneNumber(contactNumber);
        if (!isValidPhone) {
            System.out.println(" Contact number is not valid ");
            return;

        }
        System.out.println(" Gender M/F : ");
        String gender = input.nextLine().trim();


        if (isPatient) {
            System.out.println(isPatient ? "ailment details:" : "specialization details:");
            String details = input.nextLine().trim();
            PatientService.addPatient(name, age, contactNumber, gender, details);
        } else {

            // Show available specializations

            Specialization specialization = getSpecialization();
            System.out.println(" enter consultationFee : ");
            String consultationFee = input.nextLine().trim();
            DoctorService.addDoctor(name, age, contactNumber, gender, specialization, consultationFee);
        }


    }

    private static void addAppointment() {
        Specialization specialization = getSpecialization();
        DoctorService.searchDoctorBySpecialization(specialization);
        Scanner input = new Scanner(System.in);
        System.out.println(" Enter DoctorID from the list  : ");
        String doctorId = input.nextLine();
        System.out.println(" Enter contactnumber  to get patient   : ");
        String contactnumber = input.nextLine();
        PatientService.searchPatientByContactNumber(contactnumber);
        System.out.println(" Enter PatientID from the list  : ");
        String patientId = input.nextLine();
        System.out.println(" Enter datetime   : ");
        String datetime = input.nextLine();
        AppointmentService.createAppointment(contactnumber, patientId, doctorId, specialization, datetime);


    }

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        boolean exitMenu = true;
        try {
            while (exitMenu) {

                displayMenu();
                int i = Integer.parseInt(input.nextLine().trim());
                switch (i) {
                    case 1 -> addPersonDetails(true);
                    case 2 -> PatientService.fetchPatientList();
                    case 3 -> updatePatient();
                    case 4 -> {

                        System.out.println("-----enter contact number delete Patient details -------");
                        String number = input.nextLine().trim();
                        boolean isValidPhone = Validator.isValidPhoneNumber(number);
                        if (!isValidPhone) {
                            System.out.println(" Contact number is not valid ");
                            return;

                        }
                        System.out.print("Enter Patient ID : ");
                        String id = input.nextLine();
                        if (!id.isEmpty()) {
                            PatientService.deletePatient(number, id);
                        } else {
                            System.out.println("ID value should not be empty");
                        }
                    }
                    case 5 -> {

                        System.out.println("----- enter contact number to find the Patient details -------");
                        String number = input.nextLine().trim();
                        boolean isValidPhone = Validator.isValidPhoneNumber(number);
                        if (!isValidPhone) {
                            System.out.println(" Contact number is not valid ");
                            return;

                        }
                        PatientService.searchPatientByContactNumber(number);
                    }
                    case 6 -> addPersonDetails(false);
                    case 7 -> DoctorService.fetchDoctorList();
                    case 8 -> updateDoctor();
                    case 9 -> {
                        System.out.println("----- Enter specialization to delete Doctor details ------");
                        Specialization specialization = getSpecialization();
                        System.out.print("Enter DOC ID : ");
                        String id = input.nextLine();
                        if (!id.isEmpty()) {
                            DoctorService.deleteDoctor(specialization, id);
                        } else {
                            System.out.println("ID value should not be empty");
                        }
                    }

                    case 10 -> {
                        System.out.println("----- enter specialization  to find the Doctor details -------");
                        Specialization specialization = getSpecialization();
                        DoctorService.searchDoctorBySpecialization(specialization);
                    }
                    case 11 -> addAppointment();
                    case 12 -> AppointmentService.viewAppointments();
                    case 13->{
                        AppointmentService.viewAppointments();

                        System.out.println(" Enter Appointment ID from the list  : ");
                        String appointmentId = input.nextLine();
                        try{
                            AppointmentService.cancelAppointment(appointmentId);
                        }
                       catch(AppointmentNotFoundException e)
                       {
                           System.out.println(e.getMessage());
                       }
                    }
                    case 16 -> exitMenu = false;
                    default -> throw new InvalidInputException("Please enter a valid option!");
                }

            }
            System.out.println("Thanks you visit again");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

}
