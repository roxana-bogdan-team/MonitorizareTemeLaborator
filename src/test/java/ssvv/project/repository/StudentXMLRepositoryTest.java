package ssvv.project.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssvv.project.domain.Student;
import ssvv.project.validation.StudentValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StudentXMLRepositoryTest {

    private static final String STUDENTS_FILE = "src/test/resources/students/studenti.xml";

    private StudentXMLRepository studentXMLRepository;

    @Before
    public void setUp() throws IOException {
        Files.write(
                Path.of(STUDENTS_FILE), Collections.singleton(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                                "<Entitati>\n" +
                                "</Entitati>"
                )
        );
        studentXMLRepository = new StudentXMLRepository(new StudentValidator(), STUDENTS_FILE);
    }

    @After
    public void tearDown() {
        new File(STUDENTS_FILE).delete();
    }

    @Test
    public void testSaveNonExistingStudent() {
        final String id = "777";
        final String nume = "nume1";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);

        final Student result = studentXMLRepository.save(student);

        assertNull(result);
    }

    @Test
    public void testSaveExistingStudent() {
        final String id = "777";
        final String nume = "nume1";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);
        studentXMLRepository.save(student);

        final Student result = studentXMLRepository.save(student);

        assertEquals(student, result);
    }
}