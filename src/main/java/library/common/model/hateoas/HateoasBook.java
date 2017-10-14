package library.common.model.hateoas;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import library.common.model.Book;
import library.common.utils.hateoas.HateoasObject;
import library.common.utils.hateoas.IHateoasObject;
import library.common.utils.hateoas.serializer.BookHateoasSerializer;

import java.util.List;

import static library.common.utils.Constants.BOOK;

/**
 * Created by tsodring on 10/13/17.
 */
@JsonSerialize(using = BookHateoasSerializer.class)
public class HateoasBook extends HateoasObject implements IHateoasObject {

    public HateoasBook(ILibraryEntity entity) {
        super(entity);
    }

    public HateoasBook(List<ILibraryEntity> entityList) {
        super(entityList, BOOK);
    }
    public String getBaseTypeName() { return BOOK; }
}

