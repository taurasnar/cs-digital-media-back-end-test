package csdigitalmediabackendtest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import csdigitalmediabackendtest.models.Book;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(BookService.class)
class BookServiceTest {

    @MockBean
    private transient BookRepository bookRepository;

    @Autowired
    private transient BookService bookService;


    @Test
    void findAllTest() {
        List<Book> books =
            List.of(new Book(1L, "title1", Book.Genre.FICTION),
                new Book(2L, "title2", Book.Genre.NONFICTION),
                new Book(3L, "title3", Book.Genre.FICTION));

        when(bookRepository.findAll()).thenReturn(books);

        assertEquals(books, bookService.findAll());
    }

    @Test
    void findByIdTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        assertEquals(book, bookService.findById(book.getId()));
        assertThrows(ResourceNotFoundException.class, () -> bookService.findById(2L));
    }

    @Test
    void createTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);

        when(bookRepository.save(book)).thenReturn(book);

        assertEquals(book, bookService.create(book));
    }

    @Test
    void updateTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);
        Book newBook = new Book(1L, "title1", Book.Genre.NONFICTION);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        assertEquals(newBook, bookService.update(book, newBook));

        book.setId(2L);
        assertThrows(ResourceNotFoundException.class,
            () -> bookService.update(book, newBook));
    }

    @Test
    void deleteTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        bookService.delete(book.getId());

        verify(bookRepository, times(1)).deleteById(book.getId());
        assertThrows(ResourceNotFoundException.class, () -> bookService.delete(2L));
    }

}