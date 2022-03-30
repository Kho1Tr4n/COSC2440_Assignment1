import java.util.ArrayList;
import java.util.List;

public class Course {
    private String CourseID;
    private String name;
    private int NumberofCredits;

    private List<Course>CourseList = new ArrayList<>();

    public Course(String courseID, String name, int NumberofCredits) {
        this.CourseID = courseID;
        this.name = name;
        this.NumberofCredits = NumberofCredits;

    }

    public String getCourseID() {
        return CourseID;
    }

    public String getName() {
        return name;
    }

    public int getNumberofCredits() {
        return NumberofCredits;
    }

    public List<Course> getCourseList() {
        return CourseList;
    }

    public void addCourse(Course course){
        CourseList.add(course);
    }

    public void getAllCourse(){
        CourseList.forEach(course -> System.out.println(course.toString()));
    }


    @Override
    public String toString() {
        return "Course{" +
                "CourseID='" + CourseID + '\'' +
                ", name='" + name + '\'' +
                ", NumberofCredits=" + NumberofCredits +
                '}';
    }
}
