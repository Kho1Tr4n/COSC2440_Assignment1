import java.util.ArrayList;
import java.util.List;

public class Student {
    private String StudentID;
    private String name;
    private String birthday;
    List<Student>studentList = new ArrayList<>();


    public Student(String studentID, String name, String birthday) {
        this.StudentID = studentID;
        this.name = name;
        this.birthday = birthday;

    }

    public String getStudentID() {
        return StudentID;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Student> getStudent(){
        return studentList;
    }

    public void getAllStudent(){
        for (Student student: studentList){
            System.out.println(student.toString());
        }
    }
    @Override
    public String toString() {
        return "Student{" +
                "StudentID='" + StudentID + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}





