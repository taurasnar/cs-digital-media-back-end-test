package csdigitalmediabackendtest.models;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BOOKS")
public class Book implements Updatable<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Book.Genre genre;

    @Override
    public void update(Book other) {
        this.title = other.title;
        this.genre = other.genre;

    }

    public enum Genre {
        FICTION,
        NONFICTION
    }
}
