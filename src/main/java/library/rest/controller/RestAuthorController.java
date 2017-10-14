
package library.rest.controller;
import library.common.model.Author;
import library.common.service.IAuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@RestController
@RequestMapping(value = "/library/rest/authors")
public class RestAuthorController {

    private IAuthorService authorService;

    RestAuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Author> getAuthors() {
        return authorService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.findOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.save(author));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.save(author));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Author> updateBook(
            @PathVariable("id") Long id,
            @RequestBody Author author) throws Exception{
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.update(id, author));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Author with id " + Long.toString(id) + " was deleted");
    }
}
