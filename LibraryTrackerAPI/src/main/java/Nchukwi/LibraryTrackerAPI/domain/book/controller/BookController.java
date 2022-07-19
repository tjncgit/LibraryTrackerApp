package Nchukwi.LibraryTrackerAPI.domain.book.controller;

import Nchukwi.LibraryTrackerAPI.domain.book.model.Book;
import Nchukwi.LibraryTrackerAPI.domain.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>>  getAll() {
        Iterable<Book> book = bookService.getAllBooks();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book newBook =  bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id){
        Book book = bookService.getById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/lastName")
    public ResponseEntity<List<Book>> getByLastName(@RequestParam(value = "lastName") String lastName){
        List<Book> books = (List<Book>)bookService.getByLastName(lastName);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Book>> getByGenre(@RequestParam(value = "genre") String genre){
        List<Book> books = (List<Book>)bookService.getByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> delete(@PathVariable("id") Long id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
