package library.common.utils.hateoas;

import library.common.model.hateoas.ILibraryEntity;
import library.common.utils.hateoas.IHateoasObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tsodring on 2/6/17.
 * <p>
 * Describe Hateoas links handler
 */
public interface IHateoasHandler {

    void addLinks(IHateoasObject hateoasObject, HttpServletRequest request);
    void addSelfLink(ILibraryEntity entity, IHateoasObject hateoasNoarkObject);
    void addEntityLinks(ILibraryEntity entity, IHateoasObject hateoasNoarkObject);
}
