package csdigitalmediabackendtest.repositories;

import csdigitalmediabackendtest.entities.BookAuthor;
import csdigitalmediabackendtest.entities.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}
