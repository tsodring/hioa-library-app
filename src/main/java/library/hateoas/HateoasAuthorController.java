
package library.hateoas;
import library.common.model.Author;
import library.common.service.IAuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@RestController
@RequestMapping(value = "/library/hateoas/authors")
public class HateoasAuthorController {

    private IAuthorService authorService;

    HateoasAuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Author> getAuthors() {
        return authorService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author getAuthor(@PathVariable("id") Long id) {
        return authorService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return true;
    }

}
