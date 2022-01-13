package csdigitalmediabackendtest.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void constructorTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);
        Book book1 = new Book();

        assertNotNull(book);
        assertNotNull(book1);
    }

    @Test
    void updateTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);
        Book newBook = new Book(1L, "newTitle", Book.Genre.NONFICTION);

        book.update(newBook);

        assertEquals("newTitle", book.getTitle());
        assertEquals(Book.Genre.NONFICTION, book.getGenre());
    }

    @Test
    void gettersAndSettersTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);

        book.setId(2L);
        book.setTitle("newTitle");
        book.setGenre(Book.Genre.NONFICTION);

        assertEquals(2L, book.getId());
        assertEquals("newTitle", book.getTitle());
        assertEquals(Book.Genre.NONFICTION, book.getGenre());
    }

    @Test
    void equalsTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);
        Book bookEq = new Book(1L, "title", Book.Genre.FICTION);
        Book bookNeq = new Book(1L, "title", Book.Genre.NONFICTION);

        assertEquals(book, book);
        assertEquals(book, bookEq);
        assertNotEquals(book, bookNeq);
        assertNotEquals(book, 1L);
    }

    @Test
    void hashCodeTest() {
        Book book = new Book(1L, "title", Book.Genre.FICTION);
        int hashCode = book.hashCode();

        assertEquals(hashCode, book.hashCode());
    }
}