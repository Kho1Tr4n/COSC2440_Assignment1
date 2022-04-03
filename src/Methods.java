import java.util.List;

public interface Methods {
    public boolean add(String StudentID, String courseID, String semester);
    public boolean delete(String studentID, String courseID, String semester);
    public void update(StudentEnrollment BeUpdated, StudentEnrollment Update);
    public StudentEnrollment getOne(String StudentID, String CourseID, String semester);
    public List<StudentEnrollment> getAll();
}
