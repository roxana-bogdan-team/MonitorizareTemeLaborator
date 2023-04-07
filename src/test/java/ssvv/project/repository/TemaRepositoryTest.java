package ssvv.project.repository;

import org.junit.Before;
import org.junit.Test;
import ssvv.project.domain.Tema;
import ssvv.project.validation.TemaValidator;

import static org.junit.Assert.*;

public class TemaRepositoryTest {
    private TemaRepository temaRepository;

    @Before
    public void setUp() {
        temaRepository = new TemaRepository(new TemaValidator());
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
    public void testAddAssignmentNullId() {
        final String id1 = "1";
        final String description1 = "desc";
        final int deadline1 = 1;
        final int startline1 = 1;
        Tema tema1 = new Tema(id1, description1, deadline1, startline1);
        temaRepository.save(tema1);

        final String id2 = "1";
        final String description2 = "desc";
        final int deadline2 = 1;
        final int startline2 = 1;
        Tema tema2 = new Tema(id2, description2, deadline2, startline2);

        final Tema result2 = temaRepository.save(tema2);
        assertEquals(tema1, result2);
    }

}