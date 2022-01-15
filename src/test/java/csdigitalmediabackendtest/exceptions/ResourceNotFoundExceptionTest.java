package csdigitalmediabackendtest.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
import graphql.ErrorType;
import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {

    private final transient ResourceNotFoundException resourceNotFoundException1 =
        new ResourceNotFoundException("Book", 1L);
    private final transient ResourceNotFoundException resourceNotFoundException2 =
        new ResourceNotFoundException("Authors");

    @Test
    void getLocationsTest() {
        assertNull(resourceNotFoundException1.getLocations());
    }

    @Test
    void getExtensionsTest() {
        Map<String, Object> extensions1 = new HashMap<>();
        Map<String, Object> extensions2 = new HashMap<>();
        extensions1.put("Invalid id", 1L);

        assertEquals(extensions1, resourceNotFoundException1.getExtensions());
        assertEquals(extensions2, resourceNotFoundException2.getExtensions());
    }

    @Test
    void getErrorTypeTest() {
        assertEquals(ErrorType.DataFetchingException, resourceNotFoundException1.getErrorType());
    }

}