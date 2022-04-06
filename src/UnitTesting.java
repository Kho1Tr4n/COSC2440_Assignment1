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
    void testFileRunning(){
        StudentEnrollmentManager enrolmentSystem1 = new StudentEnrollmentManager();
        assertFalse(enrolmentSystem1.FileRunning("abc.csv"));
        assertTrue(enrolmentSystem1.FileRunning("default.csv"));
        assertEquals(15,enrolmentSystem1.getStudentEnrollmentList().size());
    }

    @Test
    void testIsStudentAvailable() {
        Student student = SEM.IsStudentAvailable("S102732");
        assertNull(SEM.IsCourseAvailable("s3916827"));
        assertNull(SEM.IsCourseAvailable("omglol"));

        assertEquals("S102732",student.getStudentID());
        assertEquals("Mark Duong",student.getName());
        assertEquals("8/28/2001",student.getBirthday());

    }

    @Test
    void testIsCourseAvailable() {
        Course course = SEM.IsCourseAvailable("COSC4030");
        assertNull(SEM.IsCourseAvailable("COSC0000"));
        assertNull(SEM.IsCourseAvailable("omglol"));

        assertEquals("COSC4030",course.getCourseID());
        assertEquals("Theory of Computation",course.getName());
        assertEquals(5,course.getNumberofCredits());

    }

    @Test
    void testIsSemAvailable() {
        assertNull(SEM.IsSemesterAvailable("2020F"));
        assertNull(SEM.IsSemesterAvailable("2022"));
        assertNull(SEM.IsSemesterAvailable("F2022"));

        assertEquals("2020C", SEM.IsSemesterAvailable("2020C"));
        assertEquals("2021A", SEM.IsSemesterAvailable("2021A"));

        }


        @Test
        void testAdd() {
            assertFalse(SEM.add("S102732","COSC4030","2020C"));

            assertTrue(SEM.add("S103723", "COSC4030", "2020B"));
            assertEquals(16,SEM.getStudentEnrollmentList().size());
            assertTrue(SEM.add("S102732","BUS2232","2020C"));
            assertEquals(17,SEM.getStudentEnrollmentList().size());

        }

        @Test
        void testDelete() {

            assertFalse(SEM.delete("S102732","COSC4030","2021A"));

            assertTrue(SEM.delete("S102732","COSC4030","2020C"));
            assertEquals(14,SEM.getStudentEnrollmentList().size());
            assertTrue(SEM.delete("S101163","BUS2232","2020C"));
            assertEquals(13,SEM.getStudentEnrollmentList().size());


        }


        @Test
        void testGetOne() {

            Student st = SEM.IsStudentAvailable("S101153");
            Student st1 = SEM.IsStudentAvailable("S101312");
            Course cs = SEM.IsCourseAvailable("COSC3321");
            Course cs1 = SEM.IsCourseAvailable("PHYS1230");

            assertEquals(st,SEM.getOne("S101312","PHYS1230","2021A").getStudent());
            assertEquals(cs,SEM.getOne("S101312","PHYS1230","2021A").getCourse());
            assertEquals("2021A",SEM.getOne("S101312","PHYS1230","2021A").getSemester());

            assertEquals(st1,SEM.getOne("S101153","COSC3321","2021A").getStudent());
            assertEquals(cs1,SEM.getOne("S101153","COSC3321","2021A").getCourse());
            assertEquals("2021A",SEM.getOne("S101153","COSC3321","2021A").getSemester());

        }

        @Test
        void testGetAll() {
            assertEquals(15, SEM.getAll().size());
        }





    }