package test.java;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("ExamplePack")
class ExampleTest {
    // TODO setup
    static Validator<Student> studentValidator;
    static Validator<Tema> temaValidator;
    static Validator<Nota> notaValidator;
    static StudentXMLRepository fileRepository1;
    static TemaXMLRepository fileRepository2;
    static NotaXMLRepository fileRepository3;
    static Service service;

    @BeforeAll
    static void setUp() {
        // This method will be executed before all tests in this class
        System.out.println("Setting up the test environment...");
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    void exampleTestCase() {
        System.out.println("Running an example test...");
        assertTrue(true, "Example assertion passed");
    }

    @Test
    void groupNumberOutOfBoundsTest1() {
        assertTrue(service.saveStudent("5", "Marius", 926));
        assertFalse(service.saveStudent("5", "John", 110));
    }

    @Test
    void groupNumberOutOfBoundsTest2() {
        assertTrue(service.saveStudent("5", "Marius", 926));
        assertFalse(service.saveStudent("5", "John", 938));
    }

    @Test
    void groupNumberInsideBoundsTest1() {
        assertTrue(service.saveStudent("6", "Marius", 926));
        assertTrue(service.saveStudent("7", "John", 111));
    }

    @Test
    void groupNumberInsideBoundsTest2() {
        assertTrue(service.saveStudent("6", "Marius", 926));
        assertTrue(service.saveStudent("7", "John", 112));
    }

    @Test
    void groupNumberInsideBoundsTest3() {
        assertTrue(service.saveStudent("6", "Marius", 926));
        assertTrue(service.saveStudent("7", "John", 936));
    }

    @Test
    void groupNumberInsideBoundsTest4() {
        assertTrue(service.saveStudent("6", "Marius", 926));
        assertTrue(service.saveStudent("7", "John", 937));
    }

    @Test
    void nameIsValidOrNullTest() {
        assertTrue(service.saveStudent("5", "John", 926));
        assertFalse(service.saveStudent("7", null, 926));
    }

    @Test
    void nameIsValidTest() {
        assertTrue(service.saveStudent("5", "John", 926));
        assertTrue(service.saveStudent("7", "Marius", 926));
    }

    @Test
    void idIsValidOrEmptyTest() {
        assertTrue(service.saveStudent("5", "John", 926));
        assertFalse(service.saveStudent("", "Marius", 926));
    }

    @Test
    void idIsValidTest() {
        assertTrue(service.saveStudent("5", "John", 926));
        assertTrue(service.saveStudent("7", "Marius", 926));
    }

    @Test
    void validAssignmentTest() {
        assertTrue(service.saveTema("1", "Description", 10, 3) == 1);
    }

    @Test
    void startlineGreaterThanDeadlineTest() {
        assertTrue(service.saveTema("1", "Descriere", 10, 11) == 0);
    }

    @Test
    void assignmentDeadlineOutOfBounds() {
        assertTrue(service.saveTema("1", "Description", 15, 5) == 0);
    }

    @Test
    void startlineOutOfHigherBoundTest() {
        assertTrue(service.saveTema("1", "Descriere", 5, 15) == 0);
    }

    @Test
    void deadlineOutOfLowerBoundTest() {
        assertTrue(service.saveTema("1", "Descriere", 0, 10) == 0);
    }

    @Test
    void startlineOutOfLowerBoundTest() {
        assertTrue(service.saveTema("1", "Descriere", 10, 0) == 0);
    }
}
