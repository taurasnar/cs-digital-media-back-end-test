package csdigitalmediabackendtest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import csdigitalmediabackendtest.entities.Author;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.repositories.AuthorRepository;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(AuthorService.class)
class AuthorServiceTest {

    @MockBean
    private transient AuthorRepository authorRepository;

    @Autowired
    private transient AuthorService authorService;

    private final transient Date date = new Date();
    private final transient Timestamp birthDate = new Timestamp(date.getTime());

    @Test
    void findAllTest() {
        List<Author> authors =
            List.of(new Author(1L, "name1", "surname1", birthDate),
                new Author(2L, "name2", "surname2", birthDate),
                new Author(3L, "name3", "surname3", birthDate));

        when(authorRepository.findAll()).thenReturn(authors);

        assertEquals(authors, authorService.findAll());
    }

    @Test
    void findByIdTest() {
        Author author = new Author(1L, "name", "surname", birthDate);

        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        when(authorRepository.findById(2L)).thenReturn(Optional.empty());

        assertEquals(author, authorService.findById(author.getId()));
        assertThrows(ResourceNotFoundException.class, () -> authorService.findById(2L));
    }

    @Test
    void createTest() {
        Author author = new Author(1L, "name", "surname", birthDate);

        when(authorRepository.save(author)).thenReturn(author);

        assertEquals(author, authorService.create(author));
    }

    @Test
    void deleteTest() {
        Author author = new Author(1L, "name", "surname", birthDate);

        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        when(authorRepository.findById(2L)).thenReturn(Optional.empty());

        authorService.delete(author.getId());

        verify(authorRepository, times(1)).deleteById(author.getId());
        assertThrows(ResourceNotFoundException.class, () -> authorService.delete(2L));
    }
}