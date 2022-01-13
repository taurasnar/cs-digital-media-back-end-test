package csdigitalmediabackendtest.services;

import csdigitalmediabackendtest.entities.Author;
import csdigitalmediabackendtest.exceptions.ResourceNotFoundException;
import csdigitalmediabackendtest.repositories.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final transient AuthorRepository authorRepository;

    /**
     * Constructs AuthorService.
     *
     * @param authorRepository author repository.
     */
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Finds all Authors in the database.
     *
     * @return List of Authors.
     */
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    /**
     * Finds Author by id.
     * Throws ResourceNotFoundException if provided id does not exist.
     *
     * @param id id of the Author to find.
     * @return Author.
     */
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Author", id);
        });
    }

    /**
     * Creates Author in the database.
     *
     * @param entity Author to be created.
     * @return Author.
     */
    public Author create(Author entity) {
        return authorRepository.save(entity);
    }

    /**
     * Deletes Author from the database.
     * Throws ResourceNotFoundException if provided id does not exist.
     *
     * @param id id of the Author to delete.
     */
    public void delete(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            authorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Author", id);
        }
    }
}
