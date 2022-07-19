package Nchukwi.LibraryTrackerAPI.domain.book.services;

import Nchukwi.LibraryTrackerAPI.domain.book.model.Book;
import Nchukwi.LibraryTrackerAPI.domain.book.repo.BookRepo;
import Nchukwi.LibraryTrackerAPI.domain.core.exceptions.ResourceCreationException;
import Nchukwi.LibraryTrackerAPI.domain.core.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book create(Book book) throws ResourceCreationException {
        Optional<Book> optional = bookRepo.findByTitle(book.getTitle());
        if(optional.isPresent() && book.equals(optional.get()))
            throw new ResourceCreationException("Book Already Exists in Database");
        return  bookRepo.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book getById(Long id) throws ResourceNotFoundException {
        return bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id does not exist: " + id));
    }

    @Override
    public Book getByTitle(String title) throws ResourceNotFoundException {
        return bookRepo.findByTitle(title)
                .orElseThrow(() ->  new ResourceNotFoundException("Book with title does not exist: " + title));
    }

    @Override
    public List<Book> getByLastName(String lastName) throws ResourceNotFoundException {
        List<Book> books = (List) bookRepo.findByAuthorLastName(lastName);
        if(books.size() == 0)
            throw new ResourceNotFoundException("Book with author last name does not exist: " + lastName);
        return books;
    }

    @Override
    public List<Book> getByGenre(String genre) {
        List<Book> books = (List) bookRepo.findByGenre(genre);
        if(books.size() == 0)
            throw new ResourceNotFoundException("Book of genre does not exist: " + genre);
        return books;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Book book = bookRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource already removed"));
        bookRepo.delete(book);
    }
}
