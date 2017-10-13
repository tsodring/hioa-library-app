package library.hateoas;

import library.common.model.Book;
import library.common.service.IBookService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@RestController
@RequestMapping(value = "/library/hateoas/books")
public class HateoasBookController {

    private IBookService bookService;

    HateoasBookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Book> getBooks() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return true;
    }

}
