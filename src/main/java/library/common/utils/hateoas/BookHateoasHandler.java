package library.common.utils.hateoas;

import library.common.model.hateoas.ILibraryEntity;
import org.springframework.stereotype.Component;

import static library.common.utils.Constants.AUTHOR;
import static library.common.utils.Constants.HATEOAS_API_PATH;
import static library.common.utils.Constants.SLASH;

/**
 * Created by tsodring on 10/14/17.
 */
@Component()
public class BookHateoasHandler extends HateoasHandler implements IBookHateoasHandler {

    public void addEntityLinks(ILibraryEntity entity, IHateoasObject hateoasObject) {
        String id = Long.toString(entity.getPkId());
        String baseType = entity.getBaseTypeName();

        hateoasObject.addLink(entity, new Link(contextPath + HATEOAS_API_PATH + SLASH + baseType + "s" +
                SLASH + id + SLASH + AUTHOR + "s", "http://abi.hioa.no/library/authors", false));
    }
}
