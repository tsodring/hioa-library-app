package library.common.utils.hateoas.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import library.common.model.hateoas.ILibraryEntity;
import library.common.utils.hateoas.IHateoasObject;

import java.io.IOException;

/**
 * Created by tsodring on 2/9/17.
 */
public interface IHateoasSerializer {
    void serializeLibraryEntity(ILibraryEntity entity, IHateoasObject hateoasObject, JsonGenerator jgen)
            throws IOException;
}
