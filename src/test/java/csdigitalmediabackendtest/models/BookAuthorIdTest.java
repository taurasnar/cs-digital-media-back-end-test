package csdigitalmediabackendtest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BookAuthorIdTest {

    @Test
    void constructorTest() {
        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);
        BookAuthorId bookAuthorId1 = new BookAuthorId();

        assertNotNull(bookAuthorId);
        assertNotNull(bookAuthorId1);
    }

    @Test
    void gettersAndSettersTest() {
        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);

        bookAuthorId.setAuthorId(2L);
        bookAuthorId.setBookId(2L);

        assertEquals(2L, bookAuthorId.getAuthorId());
        assertEquals(2L, bookAuthorId.getBookId());
    }

    @Test
    void equalsTest() {
        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);
        BookAuthorId bookAuthorIdEq = new BookAuthorId(1L, 1L);
        BookAuthorId bookAuthorIdNeq = new BookAuthorId(1L, 2L);

        assertEquals(bookAuthorId, bookAuthorId);
        assertEquals(bookAuthorId, bookAuthorIdEq);
        assertNotEquals(bookAuthorId, bookAuthorIdNeq);
        assertNotEquals(bookAuthorId, 1L);
    }

    @Test
    void hashCodeTest() {
        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);
        int hashCode = bookAuthorId.hashCode();

        assertEquals(hashCode, bookAuthorId.hashCode());
    }
}