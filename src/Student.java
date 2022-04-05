// Create elements in Student class

public class Student {
    private String StudentID;
    private String name;
    private String birthday;

    public Student(String studentID, String name, String birthday) {
        this.StudentID = studentID;
        this.name = name;
        this.birthday = birthday;

    }

    // Create get method to let other class can get the data from it
    public String getStudentID() {
        return StudentID;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }


    //Create to print the data in the Student class

    @Override
    public String toString() {
        return "StudentID='" + StudentID + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday
                ;
    }
}





