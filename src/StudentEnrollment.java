// Create some elements in the class

public class StudentEnrollment {
    protected Student student;
    protected Course course;
    protected String semester;

    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    // Create get method to let other class get the data from it

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }



    // Create the print-out method to print the elements in the class

    @Override
    public String toString() {
        return "\nStudent:" + student.getStudentID() +
                " Course:" + course.getCourseID() +
                " Semester:" + semester;
    }
}
