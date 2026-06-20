 import java.util.Scanner;

// Doctor Class
class Doctor {
    int doctorId;
    String name;
    String specialization;

    Doctor(int doctorId, String name, String specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
    }
}

// Patient Class
class Patient {
    int patientId;
    String name;
    int age;

    Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }
}

// Appointment Class
class Appointment {
    Doctor doctor;
    Patient patient;
    String timeSlot;
    String status;

    Appointment(Doctor doctor, Patient patient, String timeSlot) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
        this.status = "Booked";
    }
}

// Appointment Manager Class
class AppointmentManager {
    Appointment[] appointments = new Appointment[100];
    int count = 0;

    boolean isSlotAvailable(Doctor doctor, String timeSlot) {
        for (int i = 0; i < count; i++) {
            if (appointments[i].doctor.doctorId == doctor.doctorId &&
                    appointments[i].timeSlot.equals(timeSlot) &&
                    appointments[i].status.equals("Booked")) {
                return false;
            }
        }
        return true;
    }

    void bookAppointment(Doctor doctor, Patient patient, String timeSlot) {
        if (isSlotAvailable(doctor, timeSlot)) {
            appointments[count++] = new Appointment(doctor, patient, timeSlot);
            System.out.println("Appointment booked successfully!");
        } else {
            System.out.println("Error: Slot not available.");
        }
    }

    void cancelAppointment(int patientId, String timeSlot) {
        for (int i = 0; i < count; i++) {
            if (appointments[i].patient.patientId == patientId &&
                    appointments[i].timeSlot.equals(timeSlot) &&
                    appointments[i].status.equals("Booked")) {

                appointments[i].status = "Cancelled";
                System.out.println("Appointment cancelled successfully!");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    void displayAppointments() {
        if (count == 0) {
            System.out.println("No appointments found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Doctor: " + appointments[i].doctor.name +
                    " | Patient: " + appointments[i].patient.name +
                    " | Time: " + appointments[i].timeSlot +
                    " | Status: " + appointments[i].status);
        }
    }
}

// MAIN CLASS (IMPORTANT)
public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample Data
        Doctor[] doctors = {
                new Doctor(1, "Dr. Sharma", "Cardiologist"),
                new Doctor(2, "Dr. Mehta", "Dermatologist")
        };

        Patient[] patients = {
                new Patient(1, "Rahul", 22),
                new Patient(2, "Aayush", 20)
        };

        AppointmentManager manager = new AppointmentManager();

        int choice;

        do {
            System.out.println("\n--- Hospital Appointment Booking System ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. View Appointments");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int pId = sc.nextInt();

                    System.out.print("Enter Doctor ID: ");
                    int dId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Time Slot: ");
                    String slot = sc.nextLine();

                    Patient selectedPatient = null;
                    Doctor selectedDoctor = null;

                    for (Patient p : patients) {
                        if (p.patientId == pId) {
                            selectedPatient = p;
                            break;
                        }
                    }

                    for (Doctor d : doctors) {
                        if (d.doctorId == dId) {
                            selectedDoctor = d;
                            break;
                        }
                    }

                    if (selectedPatient != null && selectedDoctor != null) {
                        manager.bookAppointment(selectedDoctor, selectedPatient, slot);
                    } else {
                        System.out.println("Invalid Patient or Doctor ID.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    int cancelId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Time Slot: ");
                    String cancelSlot = sc.nextLine();

                    manager.cancelAppointment(cancelId, cancelSlot);
                    break;

                case 3:
                    manager.displayAppointments();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}


