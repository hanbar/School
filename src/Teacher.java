import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private final String name;
    private final List<Subject> subjects = new ArrayList<>();

    public Teacher(String name) {
        this.name = name;
    }

    public void teachSubject(Subject subject) {
        subjects.add(subject);
    }
}
