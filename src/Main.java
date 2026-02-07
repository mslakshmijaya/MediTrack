import com.airtribe.meditrack.exception.InvalidInputException;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.Validator;

import java.util.Scanner;

public class Main {
    static void displayMenu() {
        System.out.println("----- MENU -------");
        System.out.println(":::::::  Clinic & Appointment Management System  :::::::-");
        System.out.println("----- 1.Add a Patient -------");
        System.out.println("----- 2.View all Patient details -------");
        System.out.println("----- 3.Update Patient by ID -------");
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

    private static void addPersonDetails(boolean isPatient) {
        System.out.println("-----Please enter  Patient details  -------");
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
        String details = "";
        System.out.println(isPatient ? "ailment details:" : "specialization details:");
        details = input.nextLine().trim();
        if (isPatient) {
            PatientService.addPatient(name, age, contactNumber, gender, details);
        } else {
            DoctorService.addDoctor(name, age, contactNumber, gender, details);
        }


    }

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        boolean exitMenu = true;
        try {
            while (exitMenu) {

                displayMenu();
                int i =  Integer.parseInt(input.nextLine().trim());
                switch (i) {
                    case 1 -> addPersonDetails(true);
                    case 2 -> PatientService.fetchPatientList();
                    case 5-> {

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
                    case 10 -> {

                        System.out.println("----- enter specialization  to find the Doctor details -------");
                        String specialization = input.nextLine().trim();
                        DoctorService.searchDoctorBySpecialization(specialization);
                    }
                    case 11 -> exitMenu = false;
                    default -> throw new InvalidInputException("Please enter a valid option!");
                }

            }
            System.out.println("Thanks you visit again");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

}
