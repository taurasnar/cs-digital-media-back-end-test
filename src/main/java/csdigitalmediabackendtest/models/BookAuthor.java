package csdigitalmediabackendtest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BOOK_AUTHOR_LOOKUP")
@IdClass(BookAuthorId.class)
public class BookAuthor implements Updatable<BookAuthor> {

    @Id
    @NotNull
    @Column(name = "A_ID")
    private Long authorId;

    @Id
    @NotNull
    @Column(name = "B_ID")
    private Long bookId;


    @Override
    public void update(BookAuthor other) {
        this.authorId = other.authorId;
    }
}
