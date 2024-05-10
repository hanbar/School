import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private final String name;
    private final Clazz clazz;
    private final Map<Subject, List<Grade>> grades = new HashMap<>();

    public Student(String name, Clazz clazz) {
        this.name = name;
        this.clazz = clazz;
        clazz.addStudent(this);
    }

    public String getName() {
        return name;
    }

    public void takeSubject(Subject subject) {
        grades.put(subject, new ArrayList<>());
    }

    public List<Subject> getSubjects() {
        return grades.entrySet().stream().map(entry -> entry.getKey()).toList();
    }

    public void setGrade(Subject subject, Grade grade) {
        if (!grades.containsKey(subject)) {
            throw new IllegalArgumentException("Student does not attend this subject");
        }
        grades.get(subject).add(grade);
    }

    public List<Grade> getGrades() {
        return grades.values().stream().flatMap(List::stream).toList();
    }

    public double getAverageGrade() {
        return grades.values().stream()
                .flatMap(List::stream)
                .mapToDouble(grade -> grade.getValue())
                .average()
                .orElse(0.0);
    }

    public double getAverageGradeBySubject(Subject subject) {
        List<Grade> gradeList = grades.get(subject);

        double total = gradeList.stream()
                .mapToInt(grade -> grade.getValue())
                .sum();

        return total / gradeList.size();
    }
}
