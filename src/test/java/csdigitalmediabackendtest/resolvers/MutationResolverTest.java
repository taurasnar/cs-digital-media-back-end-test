package csdigitalmediabackendtest.resolvers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import csdigitalmediabackendtest.enums.Genre;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.inputs.CreateAuthorInput;
import csdigitalmediabackendtest.inputs.CreateBookInput;
import csdigitalmediabackendtest.inputs.UpdateBookInput;
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

@WebMvcTest(MutationResolver.class)
class MutationResolverTest {

    @MockBean
    private transient BookRepository bookRepository;

    @MockBean
    private transient AuthorRepository authorRepository;

    @Autowired
    private transient MutationResolver mutationResolver;

    private final transient LocalDate birthDate = LocalDate.of(1990, 1, 1);

    private final transient List<Author> authors = List.of(
        new Author(1L, "name1", "surname1", birthDate),
        new Author(2L, "name2", "surname2", birthDate),
        new Author(3L, "name3", "surname3", birthDate));

    private final transient List<Long> authorsIds = List.of(1L, 2L, 3L);

    private final transient Author author = new Author(null, "name", "surname", birthDate);

    private final transient Book book = new Book(null, "title", Genre.FICTION, authors);



    @Test
    void createBookTest() {
        CreateBookInput createBookInput = new CreateBookInput("title", Genre.FICTION, authorsIds);

        when(bookRepository.save(eq(book))).thenReturn(book);
        when(authorRepository.findAllById(authorsIds)).thenReturn(authors);

        assertEquals(book, mutationResolver.createBook(createBookInput));
    }

    @Test
    void updateBookTest() {
        List<Long> updateAuthorsIds = List.of(4L, 5L, 6L);
        List<Author> updateAuthors = List.of(
            new Author(4L, "name4", "surname4", birthDate),
            new Author(5L, "name5", "surname5", birthDate),
            new Author(6L, "name6", "surname6", birthDate));

        Book updatedBook = new Book(1L, "updatedTitle", Genre.NONFICTION, updateAuthors);

        UpdateBookInput updateBookInput
            = new UpdateBookInput(1L, "updatedTitle", Genre.NONFICTION, updateAuthorsIds);
        UpdateBookInput updateBookInputInvalid
            = new UpdateBookInput(2L, "updatedTitle", Genre.NONFICTION, updateAuthorsIds);

        when(bookRepository.findById(updateBookInput.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(eq(updatedBook))).thenReturn(updatedBook);
        when(bookRepository.findById(updateBookInputInvalid.getId())).thenReturn(Optional.empty());
        when(authorRepository.findAllById(updateAuthorsIds)).thenReturn(updateAuthors);

        assertEquals(updatedBook, mutationResolver.updateBook(updateBookInput));
        assertThrows(ResourceNotFoundException.class,
            () -> mutationResolver.updateBook(updateBookInputInvalid));
    }

    @Test
    void deleteBookTest() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        assertTrue(mutationResolver.deleteBook(book.getId()));
        verify(bookRepository, times(1)).deleteById(book.getId());

        assertThrows(ResourceNotFoundException.class, () -> mutationResolver.deleteBook(2L));
    }

    @Test
    void validateAndGetAuthorsTest() {
        when(authorRepository.findAllById(authorsIds)).thenReturn(authors);

        assertEquals(authors, mutationResolver.validateAndGetAuthors(authorsIds));

        when(authorRepository.findAllById(authorsIds)).thenThrow(new IllegalArgumentException());

        assertThrows(ResourceNotFoundException.class, () -> mutationResolver.validateAndGetAuthors(authorsIds));

    }

    @Test
    void createAuthorTest() {
        CreateAuthorInput createAuthorInput = new CreateAuthorInput("name", "surname", birthDate);

        when(authorRepository.save(eq(author))).thenReturn(author);

        assertEquals(author, mutationResolver.createAuthor(createAuthorInput));
    }

}