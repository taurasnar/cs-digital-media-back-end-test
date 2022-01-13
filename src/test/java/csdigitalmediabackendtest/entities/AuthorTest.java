package csdigitalmediabackendtest.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.Test;

class AuthorTest {

    private final transient Date date = new Date();
    private final transient Timestamp birthDate = new Timestamp(date.getTime());

    @Test
    void constructorTest() {
        Author author = new Author(1L, "name", "surname", birthDate);
        Author author1 = new Author();

        assertNotNull(author);
        assertNotNull(author1);
    }

    @Test
    void gettersAndSettersTest() {
        Author author = new Author(1L, "name", "surname", birthDate);
        Date newDate = new Date();
        Timestamp newBirthDate = new Timestamp(newDate.getTime());

        author.setId(2L);
        author.setFirstName("newName");
        author.setLastName("newSurname");
        author.setBirthDate(newBirthDate);

        assertEquals(2L, author.getId());
        assertEquals("newName", author.getFirstName());
        assertEquals("newSurname", author.getLastName());
        assertEquals(newBirthDate, author.getBirthDate());
    }

    @Test
    void equalsTest() {
        Author author = new Author(1L, "name", "surname", birthDate);
        Author authorEq = new Author(1L, "name", "surname", birthDate);
        Author authorNeq = new Author(1L, "nameDiff", "diffSurname", birthDate);

        assertEquals(author, author);
        assertEquals(author, authorEq);
        assertNotEquals(author, authorNeq);
        assertNotEquals(author, 1L);
    }

    @Test
    void hashCodeTest() {
        Author author = new Author(1L, "name", "surname", birthDate);
        int hashCode = author.hashCode();

        assertEquals(hashCode, author.hashCode());
    }
}