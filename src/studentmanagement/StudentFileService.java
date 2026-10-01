package studentmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentFileService {
    private static final String FILE_NAME = "students.txt";

    public void saveStudents(StudentService service) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : service.getAllStudents()) {
                writer.write(student.getId() + "|" + student.getName() + "|" + student.getEmail() + "|" + student.getMarks() + "|" + student.getAttendance() + "|" + student.getStatus());
                writer.newLine();
            }
            System.out.println("Student details saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving student details.");
        }
    }

    public void loadStudents(StudentService service) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                double marks = Double.parseDouble(data[3]);
                int attendance = Integer.parseInt(data[4]);
                StudentStatus status = StudentStatus.valueOf(data[5]);
                Student student = new Student(id, name, email, marks, attendance, status);
                service.addStudent(student);
            }
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {
            System.out.println("No previous student data found.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid student data found in file.");
        }
    }
}
