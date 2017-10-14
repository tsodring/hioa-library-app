package library.rest.controller;

import library.common.model.Book;
import library.common.service.IBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@RestController
@RequestMapping(value = "/library/rest/books", produces = {MediaType.APPLICATION_JSON_VALUE,
                  MediaType.APPLICATION_XML_VALUE})
public class RestBookController {

    private IBookService bookService;

    RestBookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Book> getBooks() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        Book book = bookService.findOne(id);
        if (book != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(book);
        throw new EntityNotFoundException("Cannot find Book with id " + id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(book));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(
            @PathVariable("id") Long id, @RequestBody Book book) throws  Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.update(id, book));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Book with id " + Long.toString(id) + " was deleted");
    }
}
