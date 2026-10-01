package studentmanagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        StudentFileService fileService = new StudentFileService();
        
        fileService.loadStudents(service);
        
        int choice;
        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Marks");
            System.out.println("6. Update Attendance");
            System.out.println("7. Calculate Grade");
            System.out.println("8. Remove Student");
            System.out.println("9. Display Topper");
            System.out.println("10. Save Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = Double.parseDouble(scanner.nextLine());
                        if (marks < 0 || marks > 100) {
                            throw new IllegalArgumentException("Marks must be between 0 and 100.");
                        }
                        System.out.print("Enter Attendance: ");
                        int attendance = Integer.parseInt(scanner.nextLine());
                        if (attendance < 0 || attendance > 100) {
                            throw new IllegalArgumentException("Attendance must be between 0 and 100.");
                        }
                        StudentStatus status = StudentStatus.ACTIVE;
                        Student student = new Student(id, name, email, marks, attendance, status);
                        service.addStudent(student);
                        break;
                    case 2:
                        service.displayAllStudents();
                        break;
                    case 3:
                        System.out.print("Enter Student ID: ");
                        int searchId = Integer.parseInt(scanner.nextLine());
                        Student foundStudent = service.searchById(searchId);
                        System.out.println("Student Found:");
                        System.out.println(foundStudent);
                        break;
                    case 4:
                        System.out.print("Enter Student Name: ");
                        String searchName = scanner.nextLine();
                        service.searchByName(searchName);
                        break;
                    case 5:
                        System.out.print("Enter Student ID: ");
                        int marksId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Marks: ");
                        double newMarks = Double.parseDouble(scanner.nextLine());
                        service.updateMarks(marksId, newMarks);
                        break;
                    case 6:
                        System.out.print("Enter Student ID: ");
                        int attendanceId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Attendance: ");
                        int newAttendance = Integer.parseInt(scanner.nextLine());
                        service.updateAttendance(attendanceId, newAttendance);
                        break;
                    case 7:
                        System.out.print("Enter Student ID: ");
                        int gradeId = Integer.parseInt(scanner.nextLine());
                        String grade = service.calculateGrade(gradeId);
                        System.out.println("Grade: " + grade);
                        break;
                    case 8:
                        System.out.print("Enter Student ID: ");
                        int removeId = Integer.parseInt(scanner.nextLine());
                        service.removeStudent(removeId);
                        break;
                    case 9:
                        service.displayTopper();
                        break;
                    case 10:
                        fileService.saveStudents(service);
                        break;
                    case 0:
                        fileService.saveStudents(service);
                        System.out.println("Thank you for using Student Management System.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);
        
        scanner.close();
    }
}