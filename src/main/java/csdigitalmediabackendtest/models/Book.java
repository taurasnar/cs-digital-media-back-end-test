package csdigitalmediabackendtest.models;

import csdigitalmediabackendtest.enums.Genre;
import java.util.List;
import javax.persistence.*;
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
public class Book {

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
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Genre genre;

    @ManyToMany(targetEntity = Author.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "BOOK_AUTHORS", joinColumns = @JoinColumn(name = "B_ID"),
        inverseJoinColumns = @JoinColumn(name = "A_ID"))
    private List<Author> authors;

}
