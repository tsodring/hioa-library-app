package library.rest.controller;

import library.common.model.Book;
import library.common.service.IBookService;
import org.springframework.http.MediaType;
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
    public Book getBook(@PathVariable("id") Long id) {
        Book book = bookService.findOne(id);
        if (book != null)
            return book;
        throw new EntityNotFoundException("Cannot find Book with id " + id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return true;
    }
}
