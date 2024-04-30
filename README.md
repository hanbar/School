# School

_Create different entities in school. You can create all instances in Main class. Your job is to create entities with given unbreakable relationships and then print some statistical data._

In this challenge you have to design school system which have certain rules. You will practice [composition](https://stackify.com/oop-concepts-composition/) and aggregation. Your program will have very little functionality. How you design your classes matters.


**School consists of these entities:**
1. Classes - group of students (You can’t name class ‘Class’ so name it ‘Clazz’)
2. Teachers
3. Students
4. Subjects
5. Grades


**Relationships between entities (Rules):**
- Every class has one primary teacher (it’s just a role) and multiple students
- Teacher can teach multiple subjects
- Student can take multiple subjects
- Student must be member of exactly one class
- Every subject has exactly one teacher
- Student must have a grade in each subject. (Grades are between 1 and 5)

Each rule must be kept. You can use some form of validation, for example throw a RuntimeException with a message if some rule is broken.
Think carefully how you apply your rules. For example, you can have property ‘teacher’ in ‘Subject’ which represent teacher of this subject, or you can have property ‘subjects’ in ‘Teacher’ class. Or maybe you can have both? Which design is better?

**More constrains:**
- Have at least 2 classes
- Each class has at least 3 students
- Each student takes at least 3 subjects
  You can use deduction to find out how many teachers you need.
#
**Statistical data:**
1. Print sorted students by their average grades
2. Print sorted subjects by average of grades given to students
3. Print sorted classes with the best students (by average of each student’s average grade)
