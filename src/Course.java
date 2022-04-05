// Create elements in Course class

public class Course {
    private String CourseID;
    private String name;
    private int NumberofCredits;

    public Course(String courseID, String name, int NumberofCredits) {
        this.CourseID = courseID;
        this.name = name;
        this.NumberofCredits = NumberofCredits;
    }

    // Create get method to get the data easier
    public String getCourseID() {
        return CourseID;
    }

    public String getName() {
        return name;
    }

    public int getNumberofCredits() {
        return NumberofCredits;
    }

    // Create toString to print out the data in Course class

    @Override
    public String toString() {
        return "Course{" +
                "CourseID='" + CourseID + '\'' +
                ", name='" + name + '\'' +
                ", NumberofCredits=" + NumberofCredits +
                '}';
    }
}
