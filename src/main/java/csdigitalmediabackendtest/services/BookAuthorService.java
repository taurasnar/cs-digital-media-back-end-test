package csdigitalmediabackendtest.services;

import csdigitalmediabackendtest.models.BookAuthor;
import csdigitalmediabackendtest.models.BookAuthorId;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.repositories.BookAuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorService {

    private final transient BookAuthorRepository bookAuthorRepository;

    /**
     * Constructs BookAuthorService.
     *
     * @param bookAuthorRepository book-author lookup repository.
     */
    @Autowired
    public BookAuthorService(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    /**
     * Finds all BookAuthors in the database.
     *
     * @return List of BookAuthors.
     */
    public List<BookAuthor> findAll() {
        return bookAuthorRepository.findAll();
    }

    /**
     * Finds BookAuthor by id.
     * Throws ResourceNotFoundException if provided bookAuthorId does not exist.
     *
     * @param bookAuthorId id of the BookAuthor to find.
     * @return BookAuthor.
     */
    public BookAuthor findById(BookAuthorId bookAuthorId) {
        return bookAuthorRepository.findById(bookAuthorId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book Author",
                bookAuthorId.getAuthorId(), bookAuthorId.getBookId());
        });
    }

    /**
     * Creates BookAuthor in the database.
     *
     * @param entity BookAuthor to be created.
     * @return BookAuthor.
     */
    public BookAuthor create(BookAuthor entity) {
        return bookAuthorRepository.save(entity);
    }

    /**
     * Deletes BookAuthor from the database.
     * Throws ResourceNotFoundException if provided bookAuthorId does not exist.
     *
     * @param bookAuthorId id of the BookAuthor to delete.
     */
    public void delete(BookAuthorId bookAuthorId) {
        Optional<BookAuthor> bookAuthor = bookAuthorRepository.findById(bookAuthorId);
        if (bookAuthor.isPresent()) {
            bookAuthorRepository.deleteById(bookAuthorId);
        } else {
            throw new ResourceNotFoundException("Book Author",
                bookAuthorId.getAuthorId(), bookAuthorId.getBookId());
        }
    }
}
