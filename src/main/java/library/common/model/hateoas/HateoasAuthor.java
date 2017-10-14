package library.common.model.hateoas;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import library.common.utils.hateoas.HateoasObject;
import library.common.utils.hateoas.IHateoasObject;
import library.common.utils.hateoas.serializer.AuthorHateoasSerializer;

import java.util.List;

import static library.common.utils.Constants.AUTHOR;

/**
 * Created by tsodring on 10/13/17.
 */
@JsonSerialize(using = AuthorHateoasSerializer.class)
public class HateoasAuthor extends HateoasObject implements IHateoasObject {

    public HateoasAuthor(ILibraryEntity entity) {
        super(entity);
    }
    public HateoasAuthor(List<ILibraryEntity> entityList) {
        super(entityList, AUTHOR);
    }
    public String getBaseTypeName() { return AUTHOR; }
}
