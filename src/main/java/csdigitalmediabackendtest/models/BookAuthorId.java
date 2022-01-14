package csdigitalmediabackendtest.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookAuthorId implements Serializable {

    private static final long serialVersionUID = 1326071531967424766L;

    private Long authorId;

    private Long bookId;
}
