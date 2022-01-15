package csdigitalmediabackendtest.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import graphql.scalars.ExtendedScalars;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(DateScalarConfiguration.class)
class DateScalarConfigurationTest {

    @Autowired
    private transient DateScalarConfiguration dateScalarConfiguration;

    @Test
    void dateScalarTest() {
        assertEquals(ExtendedScalars.Date, dateScalarConfiguration.dateScalar());
    }

}