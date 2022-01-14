package csdigitalmediabackendtest.repositories;

import csdigitalmediabackendtest.models.BookAuthor;
import csdigitalmediabackendtest.models.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}
