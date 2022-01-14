package csdigitalmediabackendtest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BookAuthorTest {

    @Test
    void constructorTest() {
        BookAuthor bookAuthor = new BookAuthor(1L, 1L);
        BookAuthor bookAuthor1 = new BookAuthor();

        assertNotNull(bookAuthor);
        assertNotNull(bookAuthor1);
    }

    @Test
    void gettersAndSettersTest() {
        BookAuthor bookAuthor = new BookAuthor(1L, 1L);

        bookAuthor.setAuthorId(2L);
        bookAuthor.setBookId(2L);

        assertEquals(2L, bookAuthor.getAuthorId());
        assertEquals(2L, bookAuthor.getBookId());
    }

    @Test
    void equalsTest() {
        BookAuthor bookAuthor = new BookAuthor(1L, 1L);
        BookAuthor bookAuthorEq = new BookAuthor(1L, 1L);
        BookAuthor bookAuthorNeq = new BookAuthor(1L, 2L);

        assertEquals(bookAuthor, bookAuthor);
        assertEquals(bookAuthor, bookAuthorEq);
        assertNotEquals(bookAuthor, bookAuthorNeq);
        assertNotEquals(bookAuthor, 1L);
    }

    @Test
    void hashCodeTest() {
        BookAuthor bookAuthor = new BookAuthor(1L, 1L);
        int hashCode = bookAuthor.hashCode();

        assertEquals(hashCode, bookAuthor.hashCode());
    }

}