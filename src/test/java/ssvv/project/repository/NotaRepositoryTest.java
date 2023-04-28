package ssvv.project.repository;

import org.junit.Before;
import org.junit.Test;
import ssvv.project.domain.Nota;
import ssvv.project.domain.Pair;
import ssvv.project.domain.Student;
import ssvv.project.domain.Tema;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;

import static org.junit.Assert.assertNull;

public class NotaRepositoryTest {

    private StudentRepository studentRepository;
    private TemaRepository temaRepository;
    private NotaRepository notaRepository;

    @Before
    public void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
        temaRepository = new TemaRepository(new TemaValidator());
        notaRepository = new NotaRepository(new NotaValidator());
    }

    @Test
    public void testAddValidStudent() {
        final String id = "1";
        final String nume = "name";
        final int grupa = 937;
        Student student = new Student(id, nume, grupa);

        final Student result = studentRepository.save(student);

        assertNull(result);
    }

    @Test
    public void testAddValidAssignment() {
        final String id = "1";
        final String description = "desc";
        final int deadline = 1;
        final int startline = 1;
        Tema tema = new Tema(id, description, deadline, startline);

        final Tema result = temaRepository.save(tema);

        assertNull(result);
    }

    @Test
    public void testAddGradeUnit() {
        Nota nota = new Nota(new Pair<>("1", "1"), 10, 2, "lala");

        final Nota result = notaRepository.save(nota);

        assertNull(result);
    }

    @Test
    public void testAddGradeIntegrated() {
        final String idStudent = "1";
        final String numeStudent = "name";
        final int grupaStudent = 937;
        Student student = new Student(idStudent, numeStudent, grupaStudent);

        final String idAssignment = "1";
        final String descriptionAssignment = "desc";
        final int deadlineAssignment = 1;
        final int startlineAssignment = 1;
        Tema tema = new Tema(idAssignment, descriptionAssignment, deadlineAssignment, startlineAssignment);

        final int grade = 10;
        final int week = 2;
        final String feedback = "lala";

        Nota nota = new Nota(new Pair<>(idStudent, idAssignment), grade, week, feedback);

        final Student resultStudent = studentRepository.save(student);
        final Tema resultAssignment = temaRepository.save(tema);
        final Nota resultGrade = notaRepository.save(nota);

        assertNull(resultStudent);
        assertNull(resultAssignment);
        assertNull(resultGrade);
    }
}