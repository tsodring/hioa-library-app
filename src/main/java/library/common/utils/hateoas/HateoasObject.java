package library.common.utils.hateoas;


import library.common.model.hateoas.ILibraryEntity;

import java.util.*;


/**
 * This class solves the problem of adding Hateoas links to Noark entities before they are published by the core.
 * <p>
 * A HateoasNoarkObject is used for serializing Noark objects that have a systemId. A Controller will populate
 * the entityList with the results of a query to the persistence layer.  There are classes that traverses this
 * list of entities and populates corresponding entity HashMap entry with Hateoas links.
 * <p>
 * See the subclasses for details of who the serializer is that reuses the HashMap.
 * <p>
 * Scalability with the HashMap could be an issue ...
 */
public class HateoasObject implements IHateoasObject {

    /*
     * A list of noark entities comprising a result set from a query
     */
    private List<ILibraryEntity> entityList = new ArrayList<>();

    // Just testing this out
    private Iterable<ILibraryEntity> selfLinksItr;

    /**
     * When a List<ILibraryEntity> is in use, we make a note of the entityType
     */
    private String entityType;
    /**
     * A Map of noark entities -> Hateoas links e.g fonds with links
     */
    private Map<ILibraryEntity, TreeSet<Link>> hateoasMap = new HashMap<>();

    /**
     * If the Hateaos object is a list of results, then the list needs its own Hateoas Link to self
     * 1 because currently we're only adding self link, a List because this might change
     */
    private Set <Link> selfLinks = new TreeSet<>();
    /**
     * Whether or not the Hateaos object contains a single entity or a list of entities. For simplicity a list is
     * used even if the query generated a single result. Makes coding other places easier
     */
    private boolean singleEntity = true;


    public HateoasObject(ILibraryEntity entity) {
        entityList.add(entity);
    }

    public HateoasObject(List<ILibraryEntity> entityList, String entityType) {
        this.entityList.addAll(entityList);
        this.entityType = entityType;
        singleEntity = false;
    }
/*
    public HateoasNoarkObject(Iterable<ILibraryEntity> selfLinksItr) {
        this.selfLinksItr = selfLinksItr;
        singleEntity = false;
    }
*/

    @Override
    public Set<Link> getLinks(ILibraryEntity entity) {
        return hateoasMap.get(entity);
    }

    @Override
    public List<ILibraryEntity> getList() {
        return entityList;
    }

    @Override
    public void addLink(ILibraryEntity entity, Link link) {
        hateoasMap.computeIfAbsent(entity, k -> new TreeSet<Link>()).add(link);
    }

    @Override
    public void addSelfLink(Link selfLink) {
        selfLinks.add(selfLink);
    }

    @Override
    public void addLink(Link selfLink) {
        selfLinks.add(selfLink);
    }
    @Override
    public Set <Link> getSelfLinks() {
        return selfLinks;
    }

    @Override
    public boolean isSingleEntity() {
        return singleEntity;
    }

    public String getEntityType() {
        return entityType;
    }
}
