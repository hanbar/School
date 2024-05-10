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

        // Validations
        if (students.stream().anyMatch(student -> student.getSubjects().size() < 3)) {
            throw new RuntimeException("Each student has to take at least 3 subjects");
        }
        if (classes.stream().anyMatch(clazz -> clazz.getStudents().size() < 3)) {
            throw new RuntimeException("Each class has to have at least 3 students");
        }

        // Set random grades
        Random random = new Random();
        classes.forEach(clazz -> clazz.getStudents().forEach(student ->
                subjects.forEach(subject -> student.setGrade(subject, new Grade(random.nextInt(5) + 1)))
        ));

        // Statistics
        printSortedStudentsByAverageGrade(students);
        System.out.println();

        printSortedSubjectsByAverageOfGrades(subjects, students);
        System.out.println();

        printSortedClassesByBestStudents(classes);
        System.out.println();
    }

    // Print sorted students by their average grades
    public static void printSortedStudentsByAverageGrade(List<Student> students) {
        List<String> sortedStudents = students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverageGrade))
                .map(student -> student.getName() + ": "
                        + String.format("%.1f", student.getAverageGrade())
                        + " (" + student.getGrades().stream()
                        .map(Grade::getValue)
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")) + ")")
                .toList();

        System.out.println("Sorted students by their average grades:");
        sortedStudents.forEach(System.out::println);
    }

    // Print sorted subjects by average of grades given to students
    public static void printSortedSubjectsByAverageOfGrades(List<Subject> subjects, List<Student> students) {
        List<String> sortedSubjects = subjects.stream()
                .sorted(Comparator.comparingDouble(subject -> getAverageGradeForSubject(subject, students)))
                .map(subject -> subject.getName() + ": " + String.format("%.1f", getAverageGradeForSubject(subject, students)))
                .toList();

        System.out.println("Sorted subjects by average of grades given to students:");
        sortedSubjects.forEach(System.out::println);
    }

    private static double getAverageGradeForSubject(Subject subject, List<Student> students) {
        double totalAvg = students.stream()
                .mapToDouble(student -> student.getAverageGradeBySubject(subject))
                .sum();

        return totalAvg / students.size();
    }

    // Print sorted classes with the best students (by average of each student’s average grade)
    public static void printSortedClassesByBestStudents(List<Clazz> classes) {
        List<String> sortedClasses = classes.stream()
                .sorted(Comparator.comparingDouble(Main::getAverageGradeInClass))
                .map(clazz -> clazz.getName() + ": " + String.format("%.1f", getAverageGradeInClass(clazz)))
                .toList();

        System.out.println("Sorted classes with the best students:");
        sortedClasses.forEach(System.out::println);
    }

    private static double getAverageGradeInClass(Clazz clazz) {
        List<Student> students = clazz.getStudents();
        double totalAverageGrade = students.stream()
                .mapToDouble(Student::getAverageGrade)
                .sum();

        return totalAverageGrade / students.size();
    }
}