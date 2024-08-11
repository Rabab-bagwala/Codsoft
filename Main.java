import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        displayMenu();
    }

    private static void initializeData() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 30,
                "Mon/Wed/Fri 10:00-11:00"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus", 25, "Tue/Thu 14:00-15:30"));
        courses.add(new Course("ENG101", "English Literature", "Study of English literature", 20,
                "Mon/Wed/Fri 11:00-12:00"));

        students.add(new Student("S001", "John Doe"));
        students.add(new Student("S002", "Jane Smith"));
        students.add(new Student("S003", "Alice Johnson"));
    }

    private static void displayMenu() {
        int choice;
        do {
            System.out.println("\nCourse Management System:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerForCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.next();
        Student student = findStudentById(studentId);

        if (student != null) {
            System.out.print("Enter course code to register: ");
            String courseCode = scanner.next();
            Course course = findCourseByCode(courseCode);

            if (course != null) {
                if (student.registerForCourse(course)) {
                    System.out.println("Successfully registered for the course.");
                } else {
                    System.out.println("Course is full. Registration failed.");
                }
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void dropCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.next();
        Student student = findStudentById(studentId);

        if (student != null) {
            System.out.print("Enter course code to drop: ");
            String courseCode = scanner.next();
            Course course = findCourseByCode(courseCode);

            if (course != null) {
                if (student.dropCourse(course)) {
                    System.out.println("Successfully dropped the course.");
                } else {
                    System.out.println("You are not registered in this course.");
                }
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void viewRegisteredCourses() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.next();
        Student student = findStudentById(studentId);

        if (student != null) {
            System.out.println("\nRegistered Courses for " + student.getName() + ":");
            for (Course course : student.getRegisteredCourses()) {
                System.out.println(course);
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
