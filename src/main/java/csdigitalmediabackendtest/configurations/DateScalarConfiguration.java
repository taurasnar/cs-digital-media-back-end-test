package csdigitalmediabackendtest.configurations;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateScalarConfiguration  {

    @Bean
    public GraphQLScalarType dateScalar() {
        return ExtendedScalars.Date;
    }
}
