import java.util.ArrayList;
import java.util.List;

public class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int registeredStudents;
    private String schedule;
    private List<Student> students;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
        this.students = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents < capacity) {
            students.add(student);
            registeredStudents++;
            return true;
        } else {
            return false;
        }
    }

    public boolean dropStudent(Student student) {
        if (students.remove(student)) {
            registeredStudents--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nAvailable Slots: " + getAvailableSlots() +
                "\nSchedule: " + schedule + "\n";
    }
}
