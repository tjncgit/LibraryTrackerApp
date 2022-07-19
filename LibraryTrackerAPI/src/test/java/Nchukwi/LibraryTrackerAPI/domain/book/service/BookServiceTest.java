package Nchukwi.LibraryTrackerAPI.domain.book.service;

import Nchukwi.LibraryTrackerAPI.domain.book.model.Book;
import Nchukwi.LibraryTrackerAPI.domain.book.repo.BookRepo;
import Nchukwi.LibraryTrackerAPI.domain.book.services.BookService;
import Nchukwi.LibraryTrackerAPI.domain.core.exceptions.ResourceCreationException;
import Nchukwi.LibraryTrackerAPI.domain.core.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepo bookRepo;
    private Book mockBook;
    private Book saveBook;
    private Book saveBook2;

    @BeforeEach
    public void setUp() {
        String text = "https://www.google.com/books/edition/A_Game_of_Thrones/5NomkK4EV68C?hl=en&gbpv=1&printsec=frontcover";
        mockBook = new Book("Game of Thrones", "George", "Martin", "Adventure", text);
        saveBook = new Book("Game of Thrones", "George", "Martin", "Adventure", text);
        saveBook.setId(1L);
        saveBook2 = new Book("Game of Thrones", "George", "Martin", "Adventure", text);
        saveBook2.setId(2L);
    }

    @Test
    @DisplayName("Create Book - success")
    public void createBookTest01() {
        BDDMockito.doReturn(Optional.empty()).when(bookRepo).findByTitle(ArgumentMatchers.any());
        BDDMockito.doReturn(saveBook).when(bookRepo).save(mockBook);
        Book book = bookService.create(mockBook);
        Assertions.assertTrue(book.equals(mockBook));
    }

    @Test
    @DisplayName("Create Book - fail")
    public void createBookTest02() {
        BDDMockito.doReturn(Optional.of(saveBook)).when(bookRepo).findByTitle(ArgumentMatchers.any());
        Assertions.assertThrows(ResourceCreationException.class, ()-> {
            bookService.create(mockBook);
        });
    }

    @Test
    @DisplayName("Get By Id - success")
    public void getBookByIdTest01(){
        BDDMockito.doReturn(Optional.of(saveBook)).when(bookRepo).findById(1l);
        Book book = bookService.getById(1l);
        Assertions.assertNotNull(book);
    }

    @Test
    @DisplayName("Get By Id - fail")
    public void getBookByIdTest02(){
        BDDMockito.doReturn(Optional.empty()).when(bookRepo).findById(1l);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getById(1l);
        });
    }

    @Test
    @DisplayName("Get By Title - success")
    public void getBookByTitleTest01(){
        BDDMockito.doReturn(Optional.of(saveBook)).when(bookRepo).findByTitle("Game of Thrones");
        Book book = bookService.getByTitle("Game of Thrones");
        Assertions.assertNotNull(book);
    }

    @Test
    @DisplayName("Get By Title - fail")
    public void getBookByTitleTest02(){
        BDDMockito.doReturn(Optional.empty()).when(bookRepo).findByTitle("Game of Thrones");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getByTitle("Game of Thrones");
        });
    }

    @Test
    @DisplayName("Get By Last name - success")
    public void getBookByLastNameTest01(){
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(saveBook);
        mockBooks.add(saveBook2);
        BDDMockito.doReturn(mockBooks).when(bookRepo).findByAuthorLastName("Martin");
        List<Book> books = (List<Book>) bookService.getByLastName("Martin");
        Integer expected = mockBooks.size();
        Integer actual = books.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get By Last Name - fail")
    public void getBookByLastNameTest02(){
        List<Book> mockBooks = new ArrayList<>();
        BDDMockito.doReturn(mockBooks).when(bookRepo).findByAuthorLastName("Game of Thrones");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getByLastName("Game of Thrones");
        });
    }

    @Test
    @DisplayName("Get By Genre - success")
    public void getBookByGenreTest01(){
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(saveBook);
        mockBooks.add(saveBook2);
        BDDMockito.doReturn(mockBooks).when(bookRepo).findByGenre("Adventure");
        List<Book> books = (List<Book>) bookService.getByGenre("Adventure");
        Integer expected = mockBooks.size();
        Integer actual = books.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get By Genre - fail")
    public void getBookByGenreTest02(){
        List<Book> mockBooks = new ArrayList<>();
        BDDMockito.doReturn(mockBooks).when(bookRepo).findByAuthorLastName("Adventure");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getByLastName("Adventure");
        });
    }
}
