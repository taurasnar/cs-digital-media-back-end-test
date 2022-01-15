package csdigitalmediabackendtest.resolvers;

import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.inputs.CreateBookInput;
import csdigitalmediabackendtest.inputs.UpdateBookInput;
import csdigitalmediabackendtest.models.Author;
import csdigitalmediabackendtest.inputs.CreateAuthorInput;
import csdigitalmediabackendtest.models.Book;
import csdigitalmediabackendtest.repositories.AuthorRepository;
import csdigitalmediabackendtest.repositories.BookRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private final transient AuthorRepository authorRepository;
    @Autowired
    private final transient BookRepository bookRepository;

    @Autowired
    public MutationResolver(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // Operations for books

    /**
     * Creates Book and saves it in the database.
     *
     * @param createBookInput input Book.
     * @return Book.
     */
    public Book createBook(CreateBookInput createBookInput) {
        return bookRepository.save(new Book(null, createBookInput.getTitle(),
            createBookInput.getGenre(), validateAndGetAuthors(createBookInput.getAuthorsIds())));
    }

    /**
     * Updates Book in the database.
     * Throws ResourceNotFoundException if provided update book input
     * does not match any id of existing books in the database.
     *
     * @param updateBookInput update Book input.
     * @return updated Book.
     */
    public Book updateBook(UpdateBookInput updateBookInput) {
        Optional<Book> book = bookRepository.findById(updateBookInput.getId());

        if (book.isPresent()) {
            Book updateBook = new Book(updateBookInput.getId(), updateBookInput.getTitle(),
                updateBookInput.getGenre(), validateAndGetAuthors(updateBookInput.getAuthorsIds()));

            return bookRepository.save(updateBook);
        } else {
            throw new ResourceNotFoundException("Book", updateBookInput.getId());
        }
    }

    /**
     * Deletes Book from the database.
     * Throws ResourceNotFoundException if provided id
     * does not match any id of existing books in the database.
     *
     * @param id id of the Book to delete.
     */
    public boolean deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Book", id);
        }
    }

    /**
     * Helper method that validates authorsIds and gets Authors.
     * Throws ResourceNotFoundException if at least one of the Authors
     * does not exist in the database.
     *
     * @param authorsIds ids of the Authors to validate.
     * @return Authors.
     */
    public List<Author> validateAndGetAuthors(List<Long> authorsIds) {
        try {
            return authorRepository.findAllById(authorsIds);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Authors");
        }
    }

    // Operations for authors

    /**
     * Creates Author and saves it in the database.
     *
     * @param createAuthorInput input Author.
     * @return Author.
     */
    public Author createAuthor(CreateAuthorInput createAuthorInput) {
        return authorRepository.save(new Author(null, createAuthorInput.getFirstName(),
            createAuthorInput.getLastName(), createAuthorInput.getBirthDate()));
    }
}
