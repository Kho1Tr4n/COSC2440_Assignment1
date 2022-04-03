import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentEnrollmentManager implements Methods {
    private ArrayList<StudentEnrollment> StudentEnrollmentList = new ArrayList<>();
    private ArrayList<Student>studentList = new ArrayList<>();
    private List<Course>CourseList = new ArrayList<>();
    private Object StudentEnrollment;


    public void FileRunning(String path) throws IOException {
        String StudentID;
        String name;
        String birthday;
        String CourseID;
        String Coursename;
        int NumberofCredits;
        String semester;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            while ((line = br.readLine())!= null) {

                if (line.charAt(line.length() - 1) == ',') line += '0';
                String[] values = line.split(",");

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
            }
            br.close();
        } catch (FileNotFoundException a) {
            a.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ParseException, IOException {
        StudentEnrollmentManager studentEnrollmentManager = new StudentEnrollmentManager();
        studentEnrollmentManager.FileRunning("default.csv");

//        for(StudentEnrollment item : studentEnrollmentManager.StudentEnrollmentList){
//            System.out.println(item.toString());
//        }

//        String a = studentEnrollmentManager.StudentEnrollmentList.get(0).student.getStudentID();
//        String b = studentEnrollmentManager.StudentEnrollmentList.get(10).student.getStudentID();
//        System.out.println(a);
//        System.out.println(b);
//        String sid = "s131412";
//
//        boolean flag = false;
//        for(StudentEnrollment item : studentEnrollmentManager.StudentEnrollmentList) {
//            if(item.student.getStudentID().equals(sid)){
//                flag = true;
//                break;
//            }
//        }
//        while(!flag){
//            System.out.println("");
//
//            for(StudentEnrollment item : studentEnrollmentManager.StudentEnrollmentList) {
//                if(item.student.getStudentID().equals(sid)){
//                    flag = true;
//                    break;
//                }
//            }
//        }

        boolean quit = false;
        while (!quit) {
            System.out.println("\t\t\t\nEnrollment System ");
            System.out.println("1. Enroll");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("4. Report");
            System.out.println("5. Exit");

            Scanner sc = new Scanner(System.in);
            String option = "";
            System.out.print("\nPlease enter the number of the options that you want to proceed: ");
            if (sc.hasNext()) {
                option = sc.next();
            }


//            while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5) {
//                System.out.println("Please enter the number of the options that you want to proceed: ");
//                option = sc.nextInt();

            switch (option) {
                case "1":
                    Scanner sc1 = new Scanner(System.in);
                    String StudentID;

                    System.out.println("\nPlease enter the student ID: ");
                    StudentID = sc1.nextLine();
                    for (StudentEnrollment SE : studentEnrollmentManager.StudentEnrollmentList) {
                        if (SE.student.getStudentID().equals(StudentID)) {
                            System.out.println(SE.getStudentID());
                        }
                        break;
                    }
                    System.out.println("\nEnter the course ID: ");
                    String CourseID = sc1.next();
                    for (StudentEnrollment SE : studentEnrollmentManager.StudentEnrollmentList) {
                        if (SE.course.getCourseID().equals(CourseID)) ;
                        System.out.println(SE.getCourseID());

                    }

                    System.out.println("\nEnter the semester: ");
                    String semester = sc1.next();


                    break;

                case "2":

                    break;

                case "3":
                    break;

                case "4":
                    Scanner sc4 = new Scanner(System.in);
                    int ReportType = 0;
                    System.out.println("\n REPORT TYPE " +
                            "\n 1) GetOne" +
                            "\n 2) GetAll");


                    while (ReportType != 1 || ReportType != 2) {
                        System.out.println("Enter the report type: ");
                        ReportType = sc4.nextInt();
                        switch (ReportType) {
                            case 1:
                                System.out.println("Enter the student ID: ");
                                String StudentID4 = sc4.next();
                                System.out.println("Enter the Course ID: ");
                                String CourseID4 = sc4.next();
                                System.out.println("Enter the semester: ");
                                String semester4 = sc4.next();
                                for (StudentEnrollment SE : studentEnrollmentManager.StudentEnrollmentList) {
                                    if (SE.student.getStudentID().equals(StudentID4) && SE.course.getCourseID().equals(CourseID4) && SE.getSemester().equals(semester4)) {
                                        System.out.println("Student ID: " + SE.getStudentID() + " Course ID: " + SE.getCourseID());
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("DISPLAY ALL: ");
                                for (StudentEnrollment SE: studentEnrollmentManager.StudentEnrollmentList) {
                                    System.out.println("Student ID: " + SE.getStudentID() + " Course ID: " + SE.getCourseID() + " Semester: " + SE.getSemester());
                                }
                                break;
                        }
                        break;
                    }
                        break;

                    case "5":
                        System.out.println("* Goodbye and thank you for using our enrolment system *");
                        quit = true;
                        break;

                    default:
                        System.out.println("Your option is invalid");
                }
            }
        }

    public Student IsStudentAvailable(String StudentID){
        for (Student student: studentList){
            if (student.getStudentID().equals(StudentID)){
                return student;
            }
        }
        return null;
    }

    public Course IsCourseAvailable(String CourseID){
        for (Course course: CourseList){
            if (course.getCourseID().equals(CourseID)){
                return course;
            }
        }
        return null;
    }

    public StudentEnrollment IsSemesterAvailable(String semester){
        for (StudentEnrollment SE: StudentEnrollmentList ){
            if (SE.getSemester().equals(semester)){
                return SE;
            }
        }
        return null;
    }
    @Override
    public boolean add(String StudentID, String courseID, String semester) {
        Student student = IsStudentAvailable(StudentID);
        Course course = IsCourseAvailable(courseID);
        StudentEnrollment = IsSemesterAvailable(semester);
        if (student != null || course != null || semester != null) {
            for (StudentEnrollment SE: StudentEnrollmentList){
                if (student.equals(SE.getStudent()) && course.equals(SE.getCourse()) && semester.equals(SE.getSemester())) {
                    System.out.println("Enrollment is already available");
                    return false;
                }
            }
            StudentEnrollmentList.add(new StudentEnrollment(student,course,semester));
            return true;
        }
        if (student == null){
            System.out.println("Student is not available");
        }

        if(course == null){
            System.out.println("Course is not available");
        }
        if (semester == null){
            System.out.println("Semester is not valid");
        }
        return false;
    }

    @Override
    public boolean delete(String studentID, String courseID, String semester) {
        return false;
    }

    @Override
    public void update(StudentEnrollment BeUpdated, StudentEnrollment Update) {
        BeUpdated.setStudent(Update.getStudent());
        BeUpdated.setCourse(Update.getCourse());
        BeUpdated.setSemester(Update.getSemester());

        System.out.println("1 enrollment have been updated: " + BeUpdated.toString());
    }

    @Override
    public StudentEnrollment getOne(String StudentID, String CourseID, String semester) {
        return null;
    }

    @Override
    public List<StudentEnrollment> getAll() {
        return this.StudentEnrollmentList;
    }


}

