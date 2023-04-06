package ssvv.project.repository;

import org.junit.Before;
import org.junit.Test;
import ssvv.project.domain.Student;
import ssvv.project.validation.StudentValidator;

import static org.junit.Assert.*;

public class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
    }

    @Test
    public void testSaveValidStudent() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertNull(result);
    }

    @Test
    public void testSaveStudentNullId() {
        final String id = null;
        final String nume = "nume1";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveStudentEmptyId() {
        final String id = "";
        final String nume = "nume1";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveStudentNullName() {
        final String id = "1";
        final String nume = null;
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveStudentEmptyName() {
        final String id = "1";
        final String nume = "";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveStudentGroup110() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 110;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveExistingStudent() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);
        studentRepository.save(student);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveStudentGroup109() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 109;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveStudentGroup111() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 111;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertNull(result);
    }

    @Test
    public void testSaveStudentGroup938() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 938;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    public void testSaveStudentGroup939() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 939;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }
}