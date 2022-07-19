package Nchukwi.LibraryTrackerAPI.domain.book.services;

import Nchukwi.LibraryTrackerAPI.domain.book.model.Book;
import Nchukwi.LibraryTrackerAPI.domain.core.exceptions.ResourceCreationException;
import Nchukwi.LibraryTrackerAPI.domain.core.exceptions.ResourceNotFoundException;

public interface BookService {
    Book create(Book book) throws ResourceCreationException;
    Iterable<Book> getAllBooks();
    Book getById(Long id) throws ResourceNotFoundException;
    Book getByTitle(String title) throws ResourceNotFoundException;
    Iterable<Book> getByLastName(String lastName) throws ResourceNotFoundException;
    Iterable<Book> getByGenre(String genre);
    void  delete(Long id) throws ResourceNotFoundException;

}
