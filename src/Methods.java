import java.util.ArrayList;
import java.util.List;

// Create the methods for other class implements
public interface Methods {
    public boolean add(String StudentID, String courseID, String semester);
    public boolean delete(String studentID, String courseID, String semester);
    public void update(StudentEnrollment BeUpdated, StudentEnrollment Update);
    public StudentEnrollment getOne(String StudentID, String CourseID, String semester);
    public ArrayList<StudentEnrollment> getAll();
}
