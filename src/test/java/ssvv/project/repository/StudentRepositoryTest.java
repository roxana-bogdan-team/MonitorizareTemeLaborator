package ssvv.project.repository;

import org.junit.Before;
import org.junit.Test;
import ssvv.project.domain.Student;
import ssvv.project.validation.StudentValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
    }

    @Test
    public void testSaveInvalidStudent() {
        final String id = "777";
        final String nume = "nume1";
        final int grupa = 939;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertNull(result);
    }

    @Test
    public void testSaveValidStudent() {
        final String id = "777";
        final String nume = "nume1";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);
        studentRepository.save(student);

        final Student result = studentRepository.save(student);

        assertEquals(student, result);
    }
}