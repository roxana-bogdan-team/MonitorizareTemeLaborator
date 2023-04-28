package ssvv.project.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ssvv.project.domain.Student;
import ssvv.project.domain.Tema;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.validation.NotaValidator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceMockTest {
    private static final ClassLoader CLASS_LOADER = ServiceBigBangIntegrationTest.class.getClassLoader();
    private static final String GRADES_PATH = CLASS_LOADER.getResource("grades/note.xml").getFile();
    @Mock
    private StudentXMLRepository studentXMLRepository;
    @Mock
    private TemaXMLRepository temaXMLRepository;
    private Service service;

    @Before
    public void setUp() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(GRADES_PATH))) {
            br.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<Entitati>\n</Entitati>");
        } catch (final IOException e) {
            e.printStackTrace();
        }

        studentXMLRepository = mock(StudentXMLRepository.class);
        temaXMLRepository = mock(TemaXMLRepository.class);
        final NotaXMLRepository notaXMLRepository = new NotaXMLRepository(new NotaValidator(), GRADES_PATH);
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddGradeNonExistingStudentNonExistingAssignment() {
        final String idStudent = "1";
        final String idAssignment = "1";
        final int grade = 10;
        final int week = 2;
        final String feedback = "lala";

        when(studentXMLRepository.findOne(idStudent)).thenReturn(null);
        when(temaXMLRepository.findOne(idAssignment)).thenReturn(null);

        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(-1, resultGrade);
    }

    @Test
    public void testAddGradeNonExistingStudentExistingAssignment() {
        final String idStudent = "1";
        final String idAssignment = "1";
        final int grade = 10;
        final int week = 2;
        final String feedback = "lala";

        when(studentXMLRepository.findOne(idStudent)).thenReturn(null);
        when(temaXMLRepository.findOne(idAssignment)).thenReturn(new Tema(idAssignment, "desc", 2, 1));

        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(-1, resultGrade);
    }

    @Test
    public void testAddGradeExistingStudentNonExistingAssignment() {
        final String idStudent = "1";
        final String idAssignment = "1";
        final int grade = 10;
        final int week = 2;
        final String feedback = "lala";

        when(studentXMLRepository.findOne(idStudent)).thenReturn(new Student(idStudent, "1", 937));
        when(temaXMLRepository.findOne(idAssignment)).thenReturn(null);

        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(-1, resultGrade);
    }
    @Test
    public void testAddGradeWeek5Deadline2() {
        final String idStudent = "1";
        final String idAssignment = "1";
        final int grade = 10;
        final int week = 5;
        final String feedback = "lala";

        when(studentXMLRepository.findOne(idStudent)).thenReturn(new Student(idStudent, "1", 937));
        when(temaXMLRepository.findOne(idAssignment)).thenReturn(new Tema(idAssignment, "desc", 2, 1));

        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(1, resultGrade);
    }

    @Test
    public void testAddGradeWeek2Deadline5() {
        final String idStudent = "1";
        final String idAssignment = "1";
        final int grade = 1;
        final int week = 2;
        final String feedback = "lala";

        when(studentXMLRepository.findOne(idStudent)).thenReturn(new Student(idStudent, "1", 937));
        when(temaXMLRepository.findOne(idAssignment)).thenReturn(new Tema(idAssignment, "desc", 5, 1));

        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(1, resultGrade);
    }

    @Test
    public void testAddInvalidNegativeGrade() {
        final String idStudent = "1";
        final String idAssignment = "1";
        final int grade = 0;
        final int week = 2;
        final String feedback = "lala";

        when(studentXMLRepository.findOne(idStudent)).thenReturn(new Student(idStudent, "1", 937));
        when(temaXMLRepository.findOne(idAssignment)).thenReturn(new Tema(idAssignment, "desc", 1, 1));

        final int resultGrade = service.saveNota(idStudent, idAssignment, grade, week, feedback);

        assertEquals(0, resultGrade);
    }
}