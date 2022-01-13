package csdigitalmediabackendtest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Updatable<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.validation.constraints.NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "title")
    private String title;

    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Book.Genre genre;

    /**
     * Constructs Book.
     *
     * @param id id of the Book.
     * @param title title of the Book.
     * @param genre genre of the Book.
     */
    public Book(@NotNull Long id, @NotNull @NotBlank String title, @NotNull @NotBlank Book.Genre genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public Book() {
    }

    @Override
    public void update(Book other) {
        this.title = other.title;
        this.genre = other.genre;

    }

    public enum Genre {
        FICTION,
        NONFICTION
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Book)) {
            return false;
        }

        Book book = (Book) o;

        return getId().equals(book.getId())
            && getTitle().equals(book.getTitle())
            && getGenre() == book.getGenre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getGenre());
    }
}
