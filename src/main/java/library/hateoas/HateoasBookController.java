package library.hateoas;

import library.common.model.Author;
import library.common.model.Book;
import library.common.model.hateoas.HateoasAuthor;
import library.common.model.hateoas.HateoasBook;
import library.common.service.IBookService;
import library.common.utils.hateoas.serializer.IHateoasHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@RestController
@RequestMapping(value = "/library/hateoas/books")
public class HateoasBookController {

    private IBookService bookService;
    private IHateoasHandler hateoasHandler;

    public HateoasBookController(IBookService bookService, IHateoasHandler hateoasHandler) {
        this.bookService = bookService;
        this.hateoasHandler = hateoasHandler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Book> getBooks() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HateoasBook> getBook(
            HttpServletRequest request,
            @PathVariable("id") Long id) {

        Book book = bookService.findOne(id);

        HateoasBook bookHateoas = new
                HateoasBook(book);
        hateoasHandler.addLinks(bookHateoas, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookHateoas);
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
