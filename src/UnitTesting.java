import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class UnitTest {
    StudentEnrollmentManager SEM;

    @BeforeEach
    void setUp(){
        SEM = new StudentEnrollmentManager();
        SEM.FileRunning("default.csv");
    }

    @Test
    void testIsStudentExisted() {
        Student student = SEM.IsStudentAvailable("S101312");
        assertEquals("S101312",student.getStudentID());
        assertEquals("Alex Mike",student.getName());
        assertEquals("10/13/1998",student.getBirthday());

        assertNull(SEM.IsCourseAvailable("s3891724"));
        assertNull(SEM.IsCourseAvailable("abcd"));
    }

    @Test
    void testIsCourseExisted() {
        Course course = SEM.IsCourseAvailable("BUS2232");
        assertEquals("BUS2232",course.getCourseID());
        assertEquals("Business Law",course.getName());
        assertEquals(3,course.getNumberofCredits());

        assertNull(SEM.IsCourseAvailable("COSC2440"));
        assertNull(SEM.IsCourseAvailable("abcd"));
    }

    @Test
    void testIsSemValid() {
        assertEquals("2021C", SEM.IsSemesterAvailable("2021C"));
        assertEquals("2020A", SEM.IsSemesterAvailable("2020A"));
        assertNull(SEM.IsSemesterAvailable("2020D"));
        assertNull(SEM.IsSemesterAvailable("2020"));
        assertNull(SEM.IsSemesterAvailable("A2020"));
    }

    @Test
    void testAdd() {
        assertTrue(SEM.add("S101312", "COSC3321", "2021A"));
        assertEquals(16,SEM.getStudentEnrollmentList().size());

        assertTrue(SEM.add("S103912","PHYS1230","2021A"));
        assertEquals(17,SEM.getStudentEnrollmentList().size());

        assertFalse(SEM.add("S101312","COSC4030","2020C"));
    }

    @Test
    void testDelete() {
        assertTrue(SEM.delete("S101312","COSC4030","2020C"));
        assertEquals(14,SEM.getStudentEnrollmentList().size());

        assertTrue(SEM.delete("S103723","BUS2232","2020B"));
        assertEquals(13,SEM.getStudentEnrollmentList().size());

        assertFalse(SEM.delete("S101312","COSC4030","2021A"));
    }


    @Test
    void testGetOne() {
        Student student1 = SEM.IsStudentAvailable("S101163");
        Student student2 = SEM.IsStudentAvailable("S103817");
        Course course1 = SEM.IsCourseAvailable("BUS2232");
        Course course2 = SEM.IsCourseAvailable("COSC4030");

        assertEquals(student1,SEM.getOne("S101163","BUS2232","2020C").getStudent());
        assertEquals(course1,SEM.getOne("S101163","BUS2232","2020C").getCourse());
        assertEquals("2020C",SEM.getOne("S101163","BUS2232","2020C").getSemester());

        assertEquals(student2,SEM.getOne("S103817","COSC4030","2020C").getStudent());
        assertEquals(course2,SEM.getOne("S103817","COSC4030","2020C").getCourse());
        assertEquals("2020C",SEM.getOne("S103817","COSC4030","2020C").getSemester());
    }

    @Test
    void testGetAll() {
        assertEquals(15, SEM.getAll().size());
    }

    @Test
    void testFileRunning(){
        StudentEnrollmentManager enrolmentSystem1 = new StudentEnrollmentManager();
        assertFalse(enrolmentSystem1.FileRunning("abc.csv"));
        assertTrue(enrolmentSystem1.FileRunning("default.csv"));
        assertEquals(15,enrolmentSystem1.getStudentEnrollmentList().size());
    }

}