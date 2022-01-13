package csdigitalmediabackendtest.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book-author-lookup")
@IdClass(BookAuthorId.class)
public class BookAuthor {

    @Id
    @NotNull
    @Column(name = "a_id")
    private Long authorId;

    @Id
    @NotNull
    @Column(name = "b_id")
    private Long bookId;

    /**
     * Constructs BookAuthor lookup entity.
     *
     * @param authorId id of the Author.
     * @param bookId id of the Book.
     */
    public BookAuthor(@NotNull Long authorId, @NotNull Long bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public BookAuthor() {
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
        } else if (!(o instanceof BookAuthor)) {
            return false;
        }

        BookAuthor that = (BookAuthor) o;

        return getAuthorId().equals(that.getAuthorId())
            && getBookId().equals(that.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorId(), getBookId());
    }
}
