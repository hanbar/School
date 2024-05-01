import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Nováková");
        Teacher teacher2 = new Teacher("Dvořáková");

        Subject math = new Subject("Matematika", teacher1);
        Subject physics = new Subject("Fyzika", teacher1);
        Subject biology = new Subject("Biologie", teacher2);

        teacher1.teachSubject(math);
        teacher1.teachSubject(physics);
        teacher2.teachSubject(biology);

        Clazz clazz1 = new Clazz("9.A", teacher1);
        Clazz clazz2 = new Clazz("9.B", teacher2);

        Student student1 = new Student("Alena", clazz1);
        Student student2 = new Student("Hynek", clazz1);
        Student student3 = new Student("Honza", clazz1);
        Student student4 = new Student("Blanka", clazz2);
        Student student5 = new Student("Petr", clazz2);
        Student student6 = new Student("Vojta", clazz2);

        List<Student> students = List.of(student1, student2, student3, student4, student5, student6);

        List<Clazz> classes = List.of(clazz1, clazz2);

        List<Subject> subjects = List.of(math, physics, biology);

        // Enroll students to subjects
        classes.forEach(clazz -> clazz.getStudents().forEach(student ->
                subjects.forEach(subject -> student.takeSubject(subject))
        ));

        // Set random grades
        Random random = new Random();
        classes.forEach(clazz -> clazz.getStudents().forEach(student ->
                subjects.forEach(subject -> student.setGrade(subject, new Grade(random.nextInt(5) + 1)))
        ));

        // Statistics
        printSortedStudentsByAverageGrade(students);
        printSortedSubjectsByAverageOfGrades(subjects, students);
        printSortedClassesByBestStudents(classes);
    }

    // Print sorted students by their average grades
    public static void printSortedStudentsByAverageGrade(List<Student> students) {
        System.out.println("Sorted students by their average grades:");

        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverageGrade))
                .forEach(student -> System.out.println(student.getName() + ": "
                        + String.format("%.1f", student.getAverageGrade())
                        + " (" + student.getGrades().stream()
                        .map(Grade::getValue)
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")) + ")"));
        System.out.println();
    }

    // Print sorted subjects by average of grades given to students
    public static void printSortedSubjectsByAverageOfGrades(List<Subject> subjects, List<Student> students) {
        System.out.println("Sorted subjects by average of grades given to students:");

        subjects.stream()
                // .sorted(Comparator.comparingDouble(subject -> avg))
                .forEach(subject -> {
                    double totalBySubject = 0;
                    for (Student student : students) {
                        totalBySubject += student.getAverageGradeBySubject(subject);
                    }
                    double avg = totalBySubject / students.size();
                    System.out.println(subject.getName() + ": " + String.format("%.1f", avg));
                });
        System.out.println();
    }

    // Print sorted classes with the best students (by average of each student’s average grade)
    public static void printSortedClassesByBestStudents(List<Clazz> classes) {
        System.out.println("Sorted classes with the best students:");

        classes.stream()
                // .sorted(Comparator.comparingDouble(clazz -> avg))
                .forEach(clazz -> {
                    double totalByStudent = 0;
                    for (Student student : clazz.getStudents()) {
                        totalByStudent += student.getAverageGrade();
                    }
                    double avg = totalByStudent / clazz.getStudents().size();
                    System.out.println(clazz.getName() + ": " + String.format("%.1f", avg));
                });
        System.out.println();
    }
}