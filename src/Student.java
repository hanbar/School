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

    public void setGrade(Subject subject, Grade grade) {
        grades.get(subject).add(grade);
    }

    public List<Grade> getGrades() {
        List<Grade> allGrades = new ArrayList<>();
        for (List<Grade> gradeList : grades.values()) {
            allGrades.addAll(gradeList);
        }
        return allGrades;
    }

    public double getAverageGrade() {
        double total = 0;
        int count = 0;
        for (List<Grade> gradeList : grades.values()) {
            for (Grade grade : gradeList) {
                total += grade.getValue();
                count++;
            }
        }
        if (count == 0) {
            throw new IllegalStateException("Student has not been graded in any subject");
        }
        return total / count;
    }

    public double getAverageGradeBySubject(Subject subject) {
        List<Grade> gradeList = grades.get(subject);

        double total = 0;
        for (Grade grade : gradeList) {
            total += grade.getValue();
        }
        return total / gradeList.size();
    }
}
