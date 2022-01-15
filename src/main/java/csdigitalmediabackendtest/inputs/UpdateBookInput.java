package csdigitalmediabackendtest.inputs;

import csdigitalmediabackendtest.enums.Genre;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBookInput {

    private Long id;

    private String title;

    private Genre genre;

    private List<Long> authorsIds;
}
