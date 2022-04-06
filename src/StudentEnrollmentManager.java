import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentEnrollmentManager implements Methods {
    // Create ArrayList to store the data
    private final ArrayList<StudentEnrollment> StudentEnrollmentList = new ArrayList<>();
    private final ArrayList<Student>studentList = new ArrayList<>();
    private final ArrayList<Course>CourseList = new ArrayList<>();

    // Create some elements
    public boolean FileRunning(String path) {
        String StudentID;
        String name;
        String birthday;
        String CourseID;
        String Coursename;
        int NumberofCredits;
        String semester;

        // Create to read the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
        // It keep reading the file until no more line
            while ((line = br.readLine())!= null) {
                if (line.charAt(line.length() - 1) == ',') line += '0';
                String[] values = line.split(",");

        // Append each elements into each value in the file
                StudentID = values[0];
                name = values[1];
                birthday = values[2];
                CourseID = values[3];
                Coursename = values[4];
                NumberofCredits = Integer.parseInt(values[5]);
                semester = values[6];

                Student st = new Student(StudentID, name, birthday);
                Course cs = new Course(CourseID, Coursename, NumberofCredits);
                StudentEnrollment se = new StudentEnrollment(st, cs, semester);

                this.StudentEnrollmentList.add(se);
                this.studentList.add(st);
                this.CourseList.add(cs);
            }
            br.close();
        } catch (IOException a) {
            System.out.println("Invalid file!");
            return false;
        }
        return true;
    }

    // The main
    public static void main(String[] args) {
        StudentEnrollmentManager studentEnrollmentManager = new StudentEnrollmentManager();
        studentEnrollmentManager.FileRunning("default.csv");

        // Create the boolean to end the while loop
        boolean quit = false;
        // If it is not true it keep running
        while (!quit) {
            System.out.println("\t\t\t\nEnrollment System ");
            System.out.println("1. Enroll");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("4. Report");
            System.out.println("5. Display all enrollment");
            System.out.println("0. Exit\n");

            Scanner sc = new Scanner(System.in);
            String option = "";
            System.out.print("Please enter the number of the options that you want to proceed: ");
            if (sc.hasNext()) {
                option = sc.next();
            }

            // Ask the user to input some information to enroll them
            switch (option) {
                case "1":
                    Scanner sc1 = new Scanner(System.in);
                    String StudentID;
                    System.out.print("\nPlease enter the student ID: ");
                    StudentID = sc1.nextLine().toUpperCase();
                    System.out.print("\nEnter the course ID: ");
                    String CourseID = sc1.nextLine().toUpperCase();
                    System.out.print("\nEnter the semester: ");
                    String semester = sc1.nextLine().toUpperCase();
                    if (studentEnrollmentManager.add(StudentID,CourseID,semester)){
                        System.out.println("You have enrolled successfully!");
                    }
                    else{
                        System.out.println("You have enrolled fail!");
                    }
                    break;

                case "2":
                    Scanner sc2 = new Scanner(System.in);
                    System.out.print("\nPlease enter your Student ID:");
                    String StudentID2 = sc2.nextLine().toUpperCase();
//
                    System.out.println(studentEnrollmentManager.getStudentEnrollmentList(StudentID2));
                    System.out.print("\nPlease enter the Course ID you want to delete:");
                    String CourseID2 = sc2.nextLine().toUpperCase();
                    System.out.print("\nPlease enter the semester you enroll that course:");
                    String semester2 = sc2.nextLine().toUpperCase();

                    if (studentEnrollmentManager.delete(StudentID2,CourseID2,semester2)){
                        System.out.println("\nYou have deleted an enrollment");
                    } else{
                        System.out.println("\nThe enrollment have not been deleted");
                    }
                    break;

                case "3":
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Please enter your Student ID: ");
                    String StudentID3 = sc3.nextLine().toUpperCase();
                    if (studentEnrollmentManager.getStudentEnrollmentList(StudentID3) == null){
                        break;
                    }

                    System.out.println("1. Enroll" +
                                        "\n2. Delete");
                    System.out.println("Please enter your option: ");
                    String option3 = sc3.nextLine();

                    switch (option3){
                        case "1":
                            System.out.print("\nEnter the course ID: ");
                            String CourseID3 = sc3.nextLine().toUpperCase();
                            System.out.print("\nEnter the semester: ");
                            String semester3 = sc3.nextLine().toUpperCase();
                            if (studentEnrollmentManager.add(StudentID3,CourseID3,semester3)){
                                System.out.println("You have enrolled successfully!");
                                break;
                            }
                            else {
                                System.out.println("You have enrolled fail!");
                                break;
                            }

                        case "2":
                            System.out.println(studentEnrollmentManager.getStudentEnrollmentList(StudentID3));
                            System.out.print("\nPlease enter the Course ID:");
                            CourseID3 = sc3.nextLine().toUpperCase();
                            System.out.print("\nPlease enter the semester:");
                            semester3 = sc3.nextLine().toUpperCase();

                            if (studentEnrollmentManager.delete(StudentID3,CourseID3,semester3)){
                                System.out.println("You have deleted the enrollment");
                            }
                            else {
                                System.out.println("The enrollment have not been deleted");
                            }
                    }
                    break;
                case "4":
                    CREATEREPORT CR = new CREATEREPORT(studentEnrollmentManager);
                    Report report = null;
                    Scanner sc4 = new Scanner(System.in);
                    System.out.println("1. Print all student for 1 course in 1 semester");
                    System.out.println("2. Print all courses can enroll in 1 semester");
                    System.out.println("3. Print all students in 1 course in 1 semester");

                    System.out.println("Please enter your option: ");
                    String option4 = sc4.nextLine();

                    switch (option4){
                        case "1":
                            report = CR.AllCourseForStudent();
                        case"2":
                            report = CR.AllCourseInSemester();
                        case"3":
                            report = CR.AllStudentInCourseInSemester();
                            break;
                    }
                    if (report != null){
                        if (report.PrintOut()){
                            System.out.println("Do you want to save as CSV? " +
                                    "\n1. Y" +
                                    "\n2. N");
                            option4 = sc4.nextLine().toUpperCase();
                            if (option4.equals("Y")){
                                if (report.SaveCSV()){
                                    System.out.println("You have saved a CSV file");
                                    break;
                                }
                                else {
                                    System.out.println("You cannot save a CSV file");
                                    break;
                                }
                            }
                        }
                    }

                case "5":
                    System.out.println("\nDISPLAY ALL: ");
                    System.out.println(studentEnrollmentManager.getAll());
                    System.out.println("\n*Successfully display all*");
                    break;

                case "0":
                    System.out.println("* Goodbye and thank you for using our enrolment system *");
                    quit = true;
                    break;

                    default:
                        System.out.println("Your option is invalid");
                }
            }
        }

    /* Create this class to check is the student that user input is available
     * in the data or not*/
    public Student IsStudentAvailable(String StudentID){
        for (Student st: studentList){
            if (st.getStudentID().equals(StudentID.toUpperCase())){
                return st;
            }
        }
        return null;
    }

    /* Create this class to check is the course that user input is available
     * in the data or not*/

    public Course IsCourseAvailable(String CourseID){
        for (Course course: CourseList){
            if (course.getCourseID().equals(CourseID.toUpperCase())){
                return course;
            }
        }
        return null;
    }

    /* Create this class to check is the semester that user input is available
    * in the data or not*/
    public String IsSemesterAvailable(String semester){
        ArrayList<String> IsSemValid = new ArrayList<>();
        IsSemValid.add("A");
        IsSemValid.add("B");
        IsSemValid.add("C");

        // If the semester long is not equal to 5 it will return null

        if (semester.length()!= 5)
            return null;

        // This is trying to check the first 4 character of semester is number or not
        try {
            Integer.parseInt(semester.substring(0,4));
        }
        catch (Exception e){
            return null;
        }

        if (!IsSemValid.contains(semester.substring(4)))
            return null;

        return semester;
    }

    // Implement from the interface Methods and override those methods
    @Override
    public boolean add(String StudentID, String courseID, String semester) {
        Student st = IsStudentAvailable(StudentID);
        Course cs = IsCourseAvailable(courseID);
        String Semester = IsSemesterAvailable(semester);
        if (st != null && cs != null && semester != null) {
            for (StudentEnrollment SE1: StudentEnrollmentList){
                if (st.equals(SE1.getStudent()) && cs.equals(SE1.getCourse()) && Semester.equals(SE1.getSemester())) {
                    System.out.println("Enrollment is already available");
                    return false;
                }
            }
            StudentEnrollmentList.add(new StudentEnrollment(st,cs,semester));
            return true;
        }
        if (st == null){
            System.out.println("Student is not available");
        }
        if(cs == null){
            System.out.println("Course is not available");
        }
        if (Semester == null){
            System.out.println("Semester is not valid");
        }
        return false;
    }

    // Create to delete an enrollment
    @Override
    public boolean delete(String StudentID, String CourseID, String semester) {
        StudentEnrollment SE = this.getOne(StudentID,CourseID,semester);
        if (SE != null) {
            this.StudentEnrollmentList.remove(SE);
            System.out.println("You have deleted an enroll");
            return true;
        }
        return false;
    }

    @Override
    public StudentEnrollment getOne(String StudentID, String CourseID, String semester) {
        Student st = IsStudentAvailable(StudentID);
        Course cs = IsCourseAvailable(CourseID);
        String Semester = IsSemesterAvailable(semester);

        // If it satisfied the condition then it keeps going and return
        if (st != null && cs != null && semester != null){
            for (StudentEnrollment SE1: StudentEnrollmentList){
                if (StudentID.equals(SE1.getStudent().getStudentID()) && CourseID.equals(SE1.getCourse().getCourseID()) && semester.equals(SE1.getSemester())){
                    return SE1;
                }
            }
        }
        // Else it gonna display the message and return null
        if (st == null){
            System.out.println("The student you enter is not available");
        }
        if (cs == null){
            System.out.println("The course you enter is not available");
        }
        if (Semester == null){
            System.out.println("The semester you enter is not valid");
        }
        return null;
    }

    // Get all the enrollment in the file
    @Override
    public ArrayList<StudentEnrollment> getAll() {
        return this.StudentEnrollmentList;
    }

    // Create Array List to put any student in the file that have the same id into the arraylist
    public ArrayList<StudentEnrollment> getStudentEnrollmentList(String StudentID){
        ArrayList<StudentEnrollment>studentEnrollments = new ArrayList<>();
        Student st = IsStudentAvailable(StudentID);
        if (st == null){
            System.out.println("Student is not available");
            return null;
        }
        for (StudentEnrollment SE: StudentEnrollmentList){
            if (SE.getStudent().getStudentID().equals(st.getStudentID())){
                studentEnrollments.add(SE);
            }
        }
        return studentEnrollments;
    }

    public ArrayList<StudentEnrollment> getStudentEnrollmentList() {
        return StudentEnrollmentList;
    }
}




