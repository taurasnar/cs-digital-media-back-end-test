package csdigitalmediabackendtest.resolvers;

import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.models.Author;
import csdigitalmediabackendtest.models.Book;
import csdigitalmediabackendtest.repositories.AuthorRepository;
import csdigitalmediabackendtest.repositories.BookAuthorRepository;
import csdigitalmediabackendtest.repositories.BookRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final transient AuthorRepository authorRepository;

    private final transient BookRepository bookRepository;

    private final transient BookAuthorRepository bookAuthorRepository;


    public QueryResolver(AuthorRepository authorRepository, BookRepository bookRepository,
                         BookAuthorRepository bookAuthorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Finds all Authors in the database.
     *
     * @return List of Authors.
     */
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Finds Book by id.
     * Throws ResourceNotFoundException if provided id does not exist.
     *
     * @param id id of the Book to find.
     * @return Book.
     */
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book", id);
        });
    }

}
