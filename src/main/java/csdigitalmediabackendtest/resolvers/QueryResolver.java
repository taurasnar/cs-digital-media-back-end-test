package csdigitalmediabackendtest.resolvers;

import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.models.Author;
import csdigitalmediabackendtest.models.Book;
import csdigitalmediabackendtest.repositories.AuthorRepository;
import csdigitalmediabackendtest.repositories.BookRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final transient AuthorRepository authorRepository;

    private final transient BookRepository bookRepository;

    @Autowired
    public QueryResolver(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // Operations for books

    /**
     * Finds all Books in the database.
     *
     * @return List of Books.
     */
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Finds Book by id.
     * Throws ResourceNotFoundException if provided id
     * does not match any id of existing books in the database.
     *
     * @param id id of the Book to find.
     * @return Book.
     */
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book", id);
        });
    }

    // Operations for authors

    /**
     * Finds all Authors in the database.
     *
     * @return List of Authors.
     */
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Finds Author by id.
     * Throws ResourceNotFoundException if provided id
     * does not match any id of existing authors in the database.
     *
     * @param id id of the Author to find.
     * @return Author.
     */
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Author", id);
        });
    }

}
