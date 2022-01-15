package csdigitalmediabackendtest.resolvers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import csdigitalmediabackendtest.enums.Genre;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.models.Author;
import csdigitalmediabackendtest.models.Book;
import csdigitalmediabackendtest.repositories.AuthorRepository;
import csdigitalmediabackendtest.repositories.BookRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(QueryResolver.class)
class QueryResolverTest {

    @MockBean
    private transient BookRepository bookRepository;

    @MockBean
    private transient AuthorRepository authorRepository;

    @Autowired
    private transient QueryResolver queryResolver;

    private final transient LocalDate birthDate = LocalDate.of(1990, 1, 1);

    private final transient List<Author> authors = List.of(
        new Author(1L, "name1", "surname1", birthDate),
        new Author(2L, "name2", "surname2", birthDate),
        new Author(3L, "name3", "surname3", birthDate));

    private final transient List<Book> books = List.of(
        new Book(1L, "title1", Genre.FICTION, authors),
        new Book(2L, "title2", Genre.NONFICTION, authors),
        new Book(3L, "title3", Genre.FICTION, authors));

    private final transient Author author = new Author(null, "name", "surname", birthDate);

    private final transient Book book = new Book(null, "title", Genre.FICTION, authors);


    @Test
    void findAllBooksTest() {
        when(bookRepository.findAll()).thenReturn(books);

        assertEquals(books, queryResolver.findAllBooks());
    }

    @Test
    void findBookByIdTest() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        assertEquals(book, queryResolver.findBookById(book.getId()));
        assertThrows(ResourceNotFoundException.class, () -> queryResolver.findBookById(2L));
    }

    @Test
    void findAllAuthorsTest() {
        when(authorRepository.findAll()).thenReturn(authors);

        assertEquals(authors, queryResolver.findAllAuthors());
    }

    @Test
    void findAuthorByIdTest() {
        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        when(authorRepository.findById(2L)).thenReturn(Optional.empty());

        assertEquals(author, queryResolver.findAuthorById(author.getId()));
        assertThrows(ResourceNotFoundException.class, () -> queryResolver.findAuthorById(2L));
    }

}