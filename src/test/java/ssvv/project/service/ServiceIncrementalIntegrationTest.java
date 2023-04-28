package ssvv.project.service;

import org.junit.Before;
import org.junit.Test;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceIncrementalIntegrationTest {
    private static final ClassLoader CLASS_LOADER = ServiceBigBangIntegrationTest.class.getClassLoader();
    private static final String STUDENTS_PATH = CLASS_LOADER.getResource("students/studenti.xml").getFile();
    private static final String ASSIGNMENTS_PATH = CLASS_LOADER.getResource("assignments/teme.xml").getFile();
    private static final String GRADES_PATH = CLASS_LOADER.getResource("grades/note.xml").getFile();
    private static final List<String> PATHS = List.of(STUDENTS_PATH, ASSIGNMENTS_PATH, GRADES_PATH);
    private Service service;

    @Before
    public void setUp() {
        PATHS.forEach(path -> {
            try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
                br.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Entitati>\n</Entitati>");
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        final StudentXMLRepository studentXMLRepository = new StudentXMLRepository(new StudentValidator(), STUDENTS_PATH);
        final TemaXMLRepository temaXMLRepository = new TemaXMLRepository(new TemaValidator(), ASSIGNMENTS_PATH);
        final NotaXMLRepository notaXMLRepository = new NotaXMLRepository(new NotaValidator(), GRADES_PATH);
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddStudent() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 937;

        int result = service.saveStudent(id, nume, grupa);

        assertEquals(1, result);
    }

    @Test
    public void testAddStudentAssignment() {
        final String idStudent = "1";
        final String numeStudent = "name";
        final int grupaStudent = 937;

        final String idAssignment = "1";
        final String descriptionAssignment = "desc";
        final int deadlineAssignment = 1;
        final int startlineAssignment = 1;

        int resultStudent = service.saveStudent(idStudent, numeStudent, grupaStudent);
        int resultAssignment = service.saveTema(idAssignment, descriptionAssignment, deadlineAssignment, startlineAssignment);

        assertEquals(1, resultStudent);
        assertEquals(1, resultAssignment);
    }

    @Test
    public void testAddStudentAssignmentGrade() {
        final String idStudent = "1";
        final String numeStudent = "name";
        final int grupaStudent = 937;

        final String idAssignment = "1";
        final String descriptionAssignment = "desc";
        final int deadlineAssignment = 1;
        final int startlineAssignment = 1;

        final int grade = 10;
        final int week = 2;
        final String feedback = "lala";

        int resultStudent = service.saveStudent(idStudent, numeStudent, grupaStudent);
        int resultAssignment = service.saveTema(idAssignment, descriptionAssignment, deadlineAssignment, startlineAssignment);
        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(1, resultStudent);
        assertEquals(1, resultAssignment);
        assertEquals(1, resultGrade);
    }

    @Test
    public void testAddGradeIntegrated() {
        final String idStudent = "1";
        final String numeStudent = "name";
        final int grupaStudent = 937;

        final String idAssignment = "1";
        final String descriptionAssignment = "desc";
        final int deadlineAssignment = 1;
        final int startlineAssignment = 1;

        final int grade = 10;
        final int week = 2;
        final String feedback = "lala";

        final int resultStudent = service.saveStudent(idStudent, numeStudent, grupaStudent);
        final int resultAssignment = service.saveTema(idAssignment, descriptionAssignment, deadlineAssignment, startlineAssignment);
        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(1, resultStudent);
        assertEquals(1, resultAssignment);
        assertEquals(1, resultGrade);
    }
}