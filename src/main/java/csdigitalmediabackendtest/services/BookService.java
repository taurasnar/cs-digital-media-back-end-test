package csdigitalmediabackendtest.services;

import csdigitalmediabackendtest.models.Book;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final transient BookRepository bookRepository;

    /**
     * Constructs BookService.
     *
     * @param bookRepository book repository.
     */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Finds all Books in the database.
     *
     * @return List of Books.
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Finds Book by id.
     * Throws ResourceNotFoundException if provided id does not exist.
     *
     * @param id id of the Book to find.
     * @return Book.
     */
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Book", id);
        });
    }

    /**
     * Creates Book in the database.
     *
     * @param entity Book to be created.
     * @return Book.
     */
    public Book create(Book entity) {
        return bookRepository.save(entity);
    }

    /**
     * Updates Book in the database.
     * Throws ResourceNotFoundException if provided Book entity does not exist.
     *
     * @param entity Book to be updated.
     * @param updateEntity update Book.
     * @return updated Book.
     */
    public Book update(Book entity, Book updateEntity) {
        Optional<Book> book = bookRepository.findById(entity.getId());
        if (book.isPresent()) {
            entity.update(updateEntity);
            return bookRepository.save(entity);
        } else {
            throw new ResourceNotFoundException("Book", entity.getId());
        }
    }

    /**
     * Deletes Book from the database.
     * Throws ResourceNotFoundException if provided id does not exist.
     *
     * @param id id of the Book to delete.
     */
    public void delete(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Book", id);
        }
    }
}
