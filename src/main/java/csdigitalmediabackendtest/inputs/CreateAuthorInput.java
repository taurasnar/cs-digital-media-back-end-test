package csdigitalmediabackendtest.inputs;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAuthorInput {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;
}
