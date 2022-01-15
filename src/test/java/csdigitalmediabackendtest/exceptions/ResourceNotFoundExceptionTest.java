package csdigitalmediabackendtest.exceptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {

    @Test
    void constructorsTest() {
        ResourceNotFoundException resourceNotFoundException1 = new ResourceNotFoundException("Book", 1L);
        ResourceNotFoundException resourceNotFoundException2 = new ResourceNotFoundException("Authors");

        assertNotNull(resourceNotFoundException1);
        assertNotNull(resourceNotFoundException2);
    }

}