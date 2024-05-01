import java.util.ArrayList;
import java.util.List;

public class Clazz {
    private final String name;
    private final Teacher primaryTeacher;
    private final List<Student> students = new ArrayList<>();

    public Clazz(String name, Teacher primaryTeacher) {
        this.name = name;
        this.primaryTeacher = primaryTeacher;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }
}
