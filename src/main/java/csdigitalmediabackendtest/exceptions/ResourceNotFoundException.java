package csdigitalmediabackendtest.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceNotFoundException extends RuntimeException implements GraphQLError {

    private static final long serialVersionUID = 4692202166344385262L;

    private Map<String, Object> extensions = new HashMap<>();

    /**
     * Constructs ResourceNotFoundException.
     *
     * @param what entity name.
     * @param id id of the entity.
     */
    public ResourceNotFoundException(String what, Long id) {
        super(what + " not found with id '" + id + "'!");
        extensions.put("Invalid id", id);
    }

    /**
     * Constructs ResourceNotFoundException.
     *
     * @param what entity name.
     */
    public ResourceNotFoundException(String what) {
        super("At least one of the" + what + "not found!");
    }


    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
