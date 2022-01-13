package csdigitalmediabackendtest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import csdigitalmediabackendtest.entities.BookAuthor;
import csdigitalmediabackendtest.entities.BookAuthorId;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.repositories.BookAuthorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(BookAuthorService.class)
class BookAuthorServiceTest {

    @MockBean
    private transient BookAuthorRepository bookAuthorRepository;

    @Autowired
    private transient BookAuthorService bookAuthorService;


    @Test
    void findAllTest() {
        List<BookAuthor> bookAuthors =
            List.of(new BookAuthor(1L, 1L),
                new BookAuthor(2L, 2L),
                new BookAuthor(3L, 3L));


        when(bookAuthorRepository.findAll()).thenReturn(bookAuthors);

        assertEquals(bookAuthors, bookAuthorService.findAll());
    }

    @Test
    void findByIdTest() {
        BookAuthor bookAuthor = new BookAuthor(1L, 1L);
        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);
        BookAuthorId nonExistingBookAuthorId = new BookAuthorId(2L, 2L);

        when(bookAuthorRepository.findById(bookAuthorId)).thenReturn(Optional.of(bookAuthor));
        when(bookAuthorRepository.findById(nonExistingBookAuthorId)).thenReturn(Optional.empty());

        assertEquals(bookAuthor, bookAuthorService.findById(bookAuthorId));
        assertThrows(ResourceNotFoundException.class,
            () -> bookAuthorService.findById(nonExistingBookAuthorId));
    }

    @Test
    void createTest() {
        BookAuthor bookAuthor = new BookAuthor(1L, 1L);

        when(bookAuthorRepository.save(bookAuthor)).thenReturn(bookAuthor);

        assertEquals(bookAuthor, bookAuthorService.create(bookAuthor));
    }

    @Test
    void deleteTest() {
        BookAuthor bookAuthor = new BookAuthor(1L, 1L);
        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);
        BookAuthorId nonExistingBookAuthorId = new BookAuthorId(2L, 2L);

        when(bookAuthorRepository.findById(bookAuthorId)).thenReturn(Optional.of(bookAuthor));
        when(bookAuthorRepository.findById(nonExistingBookAuthorId)).thenReturn(Optional.empty());

        bookAuthorService.delete(bookAuthorId);

        verify(bookAuthorRepository, times(1)).deleteById(bookAuthorId);
        assertThrows(ResourceNotFoundException.class,
            () -> bookAuthorService.delete(nonExistingBookAuthorId));
    }

}