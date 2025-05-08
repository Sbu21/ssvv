package test.java;

import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

public class MockedServiceTest {

    StudentXMLRepository studentRepository;
    TemaXMLRepository temaRepository;
    NotaXMLRepository notaRepository;
    Service service;

    @BeforeEach
    void setup() {
        studentRepository = mock(StudentXMLRepository.class);
        temaRepository = mock(TemaXMLRepository.class);
        notaRepository = mock(NotaXMLRepository.class);

        service = new Service(studentRepository, temaRepository, notaRepository);
    }

    @Test
    void testAddStudentWithMock() {
        Student student = new Student("1", "John", 936);
        when(studentRepository.save(any(Student.class))).thenReturn(null);

        boolean result = service.saveStudent("1", "John", 936);
        assertTrue(result);

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testAddAssignmentWithMock() {
        Tema tema = new Tema("1", "Tema", 10, 8);
        when(temaRepository.save(any(Tema.class))).thenReturn(null);

        boolean result = service.saveTema("1", "Tema", 10, 8);
        assertTrue(result);

        verify(temaRepository, times(1)).save(any(Tema.class));
    }
}
