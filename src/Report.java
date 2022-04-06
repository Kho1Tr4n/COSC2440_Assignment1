import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Report {
    StudentEnrollmentManager SEM;

    // Create the elements and method for other class can easier to override

    public Report(StudentEnrollmentManager SEM) {
        this.SEM = SEM;
    }
    public abstract boolean PrintOut();
    public abstract boolean SaveCSV();
}

    class ReportofStudent extends Report {
        private String semester;
        private String path;
        private ArrayList<Student> studentslist = new ArrayList<>();
        private Course cs;

        public ReportofStudent(StudentEnrollmentManager SEM,Course cs, String semester ) {
            super(SEM);
            this.semester = semester;
            this.cs = cs;
            for (StudentEnrollment SE: SEM.getAll()){
                if (SE.getCourse().equals(cs) && SE.getSemester().equals(semester)){
                    studentslist.add(SE.getStudent());
                }
            }
            path = cs.getCourseID() +"Student" + this.semester +".csv";
        }

        // Print out the student in the arraylist
        @Override
        public boolean PrintOut(){
            for (Student st: studentslist){
                System.out.println(st);
                return true;
            }
        // If there is no student it will return false
            if (studentslist.size() == 0){
                System.out.println("There is no student in this");
                return false;
            }
            return true;
        }

        // Ask the user want to save CSV file or not
        @Override
        public boolean SaveCSV(){
            if (studentslist.size() == 0){
                return false;
            }
            // Try to append the element in the arraylist to the new file
            try {
                PrintWriter printWriter = new PrintWriter(path);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Student ID, Name, BirthDay");
                for (Student st: studentslist){
                    stringBuilder.append(st.getStudentID()).append(",");
                    stringBuilder.append(st.getName()).append(",");
                    stringBuilder.append(st.getBirthday()).append("\n");
                }
                printWriter.write(stringBuilder.toString());
                printWriter.flush();
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("The file cannot be created");
                return false;
            }
        }
    }
    class ReportofCourse extends Report {
        private String semester;
        private String path;
        private ArrayList<Course> courseslist = new ArrayList<>();
        private Student st;


        public ReportofCourse(StudentEnrollmentManager SEM, Student st,String semester) {
            super(SEM);
            this.semester = semester;
            this.st = st;
            for (StudentEnrollment SE: SEM.getAll()){
                if (SE.getStudent().equals(st) && SE.getSemester().equals(semester)){
                    courseslist.add(SE.getCourse());
                }
            }
            path = st.getStudentID() +"Course" + this.semester +".csv";
        }

        public ReportofCourse(StudentEnrollmentManager SEM, String semester) {
            super(SEM);
            this.semester = semester;
            for (StudentEnrollment SE: SEM.getAll()) {
                if (SE.getSemester().equals(semester) && !courseslist.contains(SE.getCourse())){
                    courseslist.add(SE.getCourse());
                }
            }
        }

        @Override
        public boolean PrintOut(){
            for (Course cs: courseslist){
                System.out.println(cs);
                return true;
            }
            if (courseslist.size() == 0){
                System.out.println("There is no course in this");
                return false;
            }
            return true;
        }

        @Override
        public boolean SaveCSV(){
            if (courseslist.size() == 0){
                return false;
            }
            try {
                PrintWriter printWriter = new PrintWriter(path);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Student ID, Name, BirthDay");
                for (Course cs: courseslist){
                    stringBuilder.append(cs.getCourseID()).append(",");
                    stringBuilder.append(cs.getName()).append(",");
                    stringBuilder.append(cs.getNumberofCredits()).append("\n");
                }
                printWriter.write(stringBuilder.toString());
                printWriter.flush();
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("The file cannot be created");
                return false;
            }
        }
    }

class CREATEREPORT {

        StudentEnrollmentManager SEM;


        public CREATEREPORT(StudentEnrollmentManager SEM) {
            this.SEM = SEM;
        }

        // Create to print out all the student in that course that semester
    public ReportofStudent AllStudentInCourseInSemester(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter Course ID: ");
        String CourseID = sc.nextLine();
        Course course = SEM.IsCourseAvailable(CourseID);
        if(course == null){
            System.out.println("The course you enter is not in our system");
            return null;
        }

        System.out.print("Please enter the semester: ");
        String semester = sc.nextLine();
        SEM.IsSemesterAvailable(semester);
        if(semester == null) {
            System.out.println("The semester is invalid");
            return null;
        }
        return new ReportofStudent(SEM, course, semester);
    }

    // Ask user to see All course that student currently have
        public ReportofCourse AllCourseForStudent(){
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter the Student ID: ");
            String StudentID = sc.nextLine();
            Student st = SEM.IsStudentAvailable(StudentID);
            if(st == null){
                System.out.println("Student is not available");
                return null;
            }
            System.out.print("Please enter the Semester: ");
            String semester = sc.nextLine();
            SEM.IsSemesterAvailable(semester);
            if(semester == null){
                System.out.println("The semester is not valid");
                return null;
            }
            return new ReportofCourse(SEM, st, semester);
        }

// Ask the user to input and to see all course is available in that semester
        public ReportofCourse AllCourseInSemester(){
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter the semester: ");
            String semester = sc.nextLine();
            SEM.IsSemesterAvailable(semester);
            // If it is null then it will print out invalid
            if(semester == null){
                System.out.println("\nThe semester is invalid");
                return null;
            }
            // Other wise user input right it will print out
            return new ReportofCourse(SEM, semester);
        }
    }

