
package library.hateoas;
import library.common.model.Author;
import library.common.model.hateoas.HateoasAuthor;
import library.common.model.hateoas.ILibraryEntity;
import library.common.service.IAuthorService;
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
@RequestMapping(value = "/library/hateoas/authors", produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class HateoasAuthorController {

    private IAuthorService authorService;
    private IHateoasHandler hateoasHandler;

    public HateoasAuthorController(IAuthorService authorService, IHateoasHandler hateoasHandler) {
        this.authorService = authorService;
        this.hateoasHandler = hateoasHandler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<HateoasAuthor> getAuthors(
            HttpServletRequest request) {

        ArrayList<ILibraryEntity> arrayList = new ArrayList<>();
        Set <Author> authors = authorService.findAll();
        arrayList.addAll(authors);

        HateoasAuthor authorHateoas = new
                HateoasAuthor(arrayList);
        hateoasHandler.addLinks(authorHateoas, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorHateoas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HateoasAuthor> getAuthor(
            HttpServletRequest request,
            @PathVariable("id") Long id) {

        Author author = authorService.findOne(id);

        HateoasAuthor authorHateoas = new
                HateoasAuthor(author);
        hateoasHandler.addLinks(authorHateoas, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorHateoas);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HateoasAuthor> saveAuthor(
            HttpServletRequest request,
            @RequestBody Author author) {
        Author savedAuthor = authorService.save(author);
        HateoasAuthor authorHateoas = new
                HateoasAuthor(savedAuthor);
        hateoasHandler.addLinks(authorHateoas, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorHateoas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HateoasAuthor> updateAuthor(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody Author author) throws Exception {
        Author updatedAuthor = authorService.update(id, author);
        HateoasAuthor authorHateoas = new
                HateoasAuthor(updatedAuthor);
        hateoasHandler.addLinks(authorHateoas, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorHateoas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Author with id " + Long.toString(id) + " was deleted");
    }
}
