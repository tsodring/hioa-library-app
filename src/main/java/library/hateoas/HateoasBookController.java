package library.hateoas;

import library.common.model.Author;
import library.common.model.Book;
import library.common.model.hateoas.HateoasAuthor;
import library.common.model.hateoas.HateoasBook;
import library.common.model.hateoas.ILibraryEntity;
import library.common.service.IBookService;
import library.common.utils.hateoas.IBookHateoasHandler;
import library.common.utils.hateoas.IHateoasHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@RestController
@RequestMapping(value = "/library/hateoas/books", produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class HateoasBookController {

    private IBookService bookService;
    private IBookHateoasHandler bookHateoasHandler;
    private IHateoasHandler hateoasHandler;

    public HateoasBookController(IBookService bookService, IBookHateoasHandler bookHateoasHandler,
                                 IHateoasHandler hateoasHandler) {
        this.bookService = bookService;
        this.bookHateoasHandler = bookHateoasHandler;
        this.hateoasHandler = hateoasHandler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<HateoasBook> getBooks(
            HttpServletRequest request) {

        ArrayList<ILibraryEntity> arrayList = new ArrayList<>();
        Set <Book> books = bookService.findAll();
        arrayList.addAll(books);

        HateoasBook bookHateoas = new
                HateoasBook(arrayList);
        bookHateoasHandler.addLinks(bookHateoas, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookHateoas);
    }

    @RequestMapping(value = "/{id}/authors", method = RequestMethod.GET)
    public ResponseEntity<HateoasAuthor> getBookAuthors(
            HttpServletRequest request,
            @PathVariable("id") Long id) throws Exception{

        Set <Author> authors = bookService.findByAuthor(id);;
        ArrayList<ILibraryEntity> arrayList = new ArrayList<>();
        arrayList.addAll(authors);

        HateoasAuthor authorHateoas = new
                HateoasAuthor(arrayList);
        hateoasHandler.addLinks(authorHateoas, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorHateoas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HateoasBook> getBook(
            HttpServletRequest request,
            @PathVariable("id") Long id) {

        Book book = bookService.findOne(id);

        HateoasBook bookHateoas = new
                HateoasBook(book);
        bookHateoasHandler.addLinks(bookHateoas, request);
        return ResponseEntity.status(HttpStatus.OK)
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
