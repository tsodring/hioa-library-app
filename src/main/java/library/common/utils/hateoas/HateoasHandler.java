package library.common.utils.hateoas;

import library.common.model.hateoas.ILibraryEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static library.common.utils.Constants.HATEOAS_API_PATH;
import static library.common.utils.Constants.SELF;
import static library.common.utils.Constants.SLASH;

/**
 * Created by tsodring on 2/6/17.
 * <p>
 * Used to add Hateoas links with information
 * <p>
 * Note that the contextServletPath is set when addLinks is called
 */
@Component()
public class HateoasHandler implements IHateoasHandler {

    protected String servletPath = "";
    protected String contextPath = "";

    @Value("${hateoas.publicAddress}")
    private String publicUrlPath;

    public void addLinks(IHateoasObject hateoasObject, HttpServletRequest request) {
        setParameters(request);

        Iterable<ILibraryEntity> entities = hateoasObject.getList();
        for (ILibraryEntity entity : entities) {
            addSelfLink(entity, hateoasObject);
            addEntityLinks(entity, hateoasObject);
        }
        // If hateoasObject is a list add a self link.
        // { "entity": [], "_links": [] }
        if (!hateoasObject.isSingleEntity()) {
            String url = this.contextPath + this.servletPath;
            Link selfLink = new Link(url, getRelSelfLink(), false);
            hateoasObject.addSelfLink(selfLink);
        }
    }

    public void addSelfLink(ILibraryEntity entity, IHateoasObject hateoasObject) {
        String id = Long.toString(entity.getPkId());
        String baseType = entity.getBaseTypeName();

        hateoasObject.addLink(entity, new Link(contextPath + HATEOAS_API_PATH + SLASH + baseType + "s" +
                SLASH + id + SLASH, getRelSelfLink(), false));
    }

    public void addEntityLinks(ILibraryEntity entity, IHateoasObject hateoasObject) {
    }

    protected String getRelSelfLink() {
        return SELF;
    }

    protected void setParameters(HttpServletRequest request) {
        // Given the following request:
        // http://localhost:8092/noark5v4/hateoas-api/arkivstruktur/registrering/ny-dokumentbeskrivelse
        // servletPath = [hateoas-api/arkivstruktur/registrering/ny-dokumentbeskrivelse]
        // contextPath = [http://localhost:8092/noark5v4/]
        // we need contextPath to build outgoing hateoas links
        // but it doesn't look like we actually need servlet path for anything within the context of hateoas links
        this.servletPath = request.getServletPath();
        this.contextPath = publicUrlPath;
    }
}

