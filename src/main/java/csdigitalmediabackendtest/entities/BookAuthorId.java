package csdigitalmediabackendtest.entities;

import java.io.Serializable;
import java.util.Objects;

public class BookAuthorId implements Serializable {

    private static final long serialVersionUID = 1326071531967424766L;

    private Long authorId;

    private Long bookId;

    /**
     * Constructs BookAuthorId.
     *
     * @param authorId id of the Author.
     * @param bookId id of the Book.
     */
    public BookAuthorId(Long authorId, Long bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public BookAuthorId() {
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof BookAuthorId)) {
            return false;
        }

        BookAuthorId that = (BookAuthorId) o;

        return getAuthorId().equals(that.getAuthorId())
            && getBookId().equals(that.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorId(), getBookId());
    }
}
