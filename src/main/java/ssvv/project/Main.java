package ssvv.project;

import ssvv.project.console.UI;
import ssvv.project.domain.Nota;
import ssvv.project.domain.Student;
import ssvv.project.domain.Tema;
import ssvv.project.repository.NotaXMLRepository;
import ssvv.project.repository.StudentXMLRepository;
import ssvv.project.repository.TemaXMLRepository;
import ssvv.project.service.Service;
import ssvv.project.validation.NotaValidator;
import ssvv.project.validation.StudentValidator;
import ssvv.project.validation.TemaValidator;
import ssvv.project.validation.Validator;

public class Main {
    public static void main(String[] args) {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        System.out.println("Saving student(id:777,nume:lala,grupa:937):");
        int result = service.saveStudent("777", "lala", 937);
        System.out.println("Status code for saving student: " + result);

        System.out.println("Saving assignment(id:777,descriere:tema.,deadline:5,startline:4):");
        result = service.saveTema("777", "tema.", 5, 4);
        System.out.println("Status code for saving tema: " + result);

        System.out.println("Saving nota(idstud:777,idtema:777,valnota:4,predata:5,feedback:?!?!?!?!):");
        result = service.saveNota("777", "777", 4, 5, "?!?!?!?!");
        System.out.println("Status code for saving nota: " + result+"\n\n");

        UI consola = new UI(service);
        consola.run();

        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }
}
