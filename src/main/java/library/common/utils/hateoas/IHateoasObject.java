package library.common.utils.hateoas;

import library.common.model.hateoas.ILibraryEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by tsodring on 10/13/17.
 */
public interface IHateoasObject {
    Set<Link> getLinks(ILibraryEntity entity);

    List<ILibraryEntity> getList();

    void addLink(ILibraryEntity entity, Link link);

    void addSelfLink(Link selfLink);

    void addLink(Link selfLink);

    Set<Link> getSelfLinks();

    boolean isSingleEntity();
}
