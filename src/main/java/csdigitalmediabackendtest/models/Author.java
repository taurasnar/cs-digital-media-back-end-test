package csdigitalmediabackendtest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;
}
