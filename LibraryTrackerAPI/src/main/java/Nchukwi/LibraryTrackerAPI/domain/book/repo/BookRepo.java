package Nchukwi.LibraryTrackerAPI.domain.book.repo;

import Nchukwi.LibraryTrackerAPI.domain.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Iterable<Book> findByAuthorLastName(String authorLastName);
    Iterable<Book> findByGenre(String Genre);
}
