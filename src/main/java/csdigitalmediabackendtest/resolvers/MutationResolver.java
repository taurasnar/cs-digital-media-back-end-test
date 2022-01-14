package csdigitalmediabackendtest.resolvers;

import csdigitalmediabackendtest.models.Author;
import csdigitalmediabackendtest.repositories.AuthorRepository;
import csdigitalmediabackendtest.repositories.BookAuthorRepository;
import csdigitalmediabackendtest.repositories.BookRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final transient AuthorRepository authorRepository;

    private final transient BookRepository bookRepository;

    private final transient BookAuthorRepository bookAuthorRepository;


    public MutationResolver(AuthorRepository authorRepository, BookRepository bookRepository,
                            BookAuthorRepository bookAuthorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public Author createAuthor(String firstName, String lastName, LocalDate birthDate) {
        return authorRepository.save(new Author(null, firstName, lastName, birthDate));
    }
}
