package Nchukwi.LibraryTrackerAPI.domain.book.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String authorFirstName;
    @NonNull
    private String authorLastName;
    @NonNull
    private String genre;
    @NonNull
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return  title.equals(book.title) && authorFirstName.equals(book.authorFirstName) && authorLastName.equals(book.authorLastName) && genre.equals(book.genre) && text.equals(book.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorFirstName, authorLastName, genre, text);
    }
}
