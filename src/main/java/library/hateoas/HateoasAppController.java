
package library.hateoas;


import library.hateoas.application.ApplicationDetails;
import library.hateoas.application.ConformityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Created by tsodring on 9/25/17.
 */
@RestController
@RequestMapping(value = "/library/hateoas/")
public class HateoasAppController {

    private static final Logger logger = LoggerFactory.getLogger(HateoasAppController.class);


    @Value("${hateoas.publicAddress}")
    private String publicUrlPath;


    /**
     * identify the interfaces the core supports
     *
     * @return the application details along with the correct response code (200 OK, or 500 Internal Error)
     */
    // API - All GET Requests (CRUD - READ)
    @RequestMapping(method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<ApplicationDetails> identify(HttpServletRequest request) {

        ArrayList<ConformityLevel> conformityLevels = new ArrayList<>();
        ConformityLevel conformityLevel = new ConformityLevel();
        conformityLevel.setHref(publicUrlPath + "/library/hateoas/authors");
        conformityLevel.setRel("http://abi.hioa.no/library/authors");
        conformityLevels.add(conformityLevel);

        conformityLevel = new ConformityLevel();
        conformityLevel.setHref(publicUrlPath + "/library/hateoas/books");
        conformityLevel.setRel("http://abi.hioa.no/library/books");
        conformityLevels.add(conformityLevel);

        ApplicationDetails applicationDetails = new ApplicationDetails(conformityLevels);

        return ResponseEntity.status(HttpStatus.OK)
                .body(applicationDetails);
    }
}
