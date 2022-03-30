import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class StudentEnrollmentManager {
    public static void main(String[] args) throws ParseException, IOException {
        StudentEnrollment se = FileRunning("default.csv");
        while (true) {
            Scanner sc = new Scanner(System.in);
            int option = 0;

            System.out.println("\t\t\tEnrollment System ");
            System.out.println("1. Enroll");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("4. Report");
            System.out.println("5. Exit");

            while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5) {
                System.out.println("Please enter the number of the options that you want to proceed: ");
                option = sc.nextInt();

                switch (option){
                    case 1:
                        Scanner sc1 = new Scanner(System.in);
                        String StudentID = null;

                        while (StudentID == null) {
                            System.out.println("Please enter the student ID: ");
                            StudentID = sc1.nextLine();


                        }



                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:

                        break;

                    case 5:
                        int ExitOptions = 0;
                        System.out.println("Exit options: ");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        while (true) {
                            Scanner sc5 = new Scanner(System.in);
                            System.out.print("Please enter the number of options we have: ");
                            ExitOptions = sc5.nextInt();
                            if (ExitOptions == 1){
                                break;
                            }
                            if (ExitOptions == 2){
                                sc5.close();
                                return;
                            }
                        }
                    default:
                        System.out.println("Your option is invalid");
                }
            }
         }
    }
    public static StudentEnrollment FileRunning(String path) throws IOException {
        String StudentID;
        String name;
        String birthday;
        String CourseID;
        String Coursename;
        int NumberofCredits;
        String semester;

        StudentEnrollment se = new StudentEnrollment();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String>ListofInfo = new ArrayList<>();

            String line = br.readLine();
            while(line != null) {
                ListofInfo.add(line);
                line = br.readLine();

                if (line.charAt(line.length() - 1) == ',') line += '0';
                String[] values = line.split(",");

                StudentID = values[0];
                name = values[1];
                birthday = values[2];
                CourseID = values[3];
                Coursename = values[4];
                NumberofCredits = Integer.parseInt(values[5]);
                semester = values[6];

                Student st = new Student(StudentID,name,birthday);
                Course cs = new Course(CourseID,Coursename,NumberofCredits);
                se.addStudent(st);
                se.addCourse(cs);
            }
            br.close();
        } catch (FileNotFoundException a) {
            a.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return se;
    }
}
