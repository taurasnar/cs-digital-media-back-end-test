package csdigitalmediabackendtest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4692202166344385262L;

    /**
     * Constructs ResourceNotFoundException.
     *
     * @param what entity name.
     * @param id id of the entity.
     */
    public ResourceNotFoundException(String what, Long id) {
        super(StringUtils.capitalize(what) + " not found with id '" + id + "'!");
    }

    /**
     * Constructs ResourceNotFoundException.
     *
     * @param what entity name.
     */
    public ResourceNotFoundException(String what) {
        super("At least one of the" + StringUtils.capitalize(what) + "not found!");
    }
}
