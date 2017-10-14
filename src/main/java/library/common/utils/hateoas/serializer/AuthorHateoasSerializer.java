package library.common.utils.hateoas.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import library.common.model.Author;
import library.common.model.hateoas.ILibraryEntity;
import library.common.utils.hateoas.IHateoasObject;
import java.io.IOException;

import static library.common.utils.Constants.AUTHOR_FIRST_NAME;
import static library.common.utils.Constants.AUTHOR_LAST_NAME;
import static library.common.utils.Constants.PKID;

/**
 *
 * Serialise an outgoing Author object as JSON.
 *
 *
 */
public class AuthorHateoasSerializer extends HateoasSerializer implements IHateoasSerializer {

    @Override
    public void serializeLibraryEntity(ILibraryEntity libraryEntity,
                                     IHateoasObject authorHateoas, JsonGenerator jgen) throws IOException {
        Author author = (Author) libraryEntity;
        jgen.writeStartObject();

        if (author != null && author.getPkId() != null) {
            jgen.writeNumberField(PKID, author.getPkId().longValue());
        }

        if (author != null && author.getAuthorFirstName() != null) {
            jgen.writeStringField(AUTHOR_FIRST_NAME, author.getAuthorFirstName());
        }

        if (author != null && author.getAuthorLastName() != null) {
            jgen.writeStringField(AUTHOR_LAST_NAME, author.getAuthorLastName());
        }

        printHateoasLinks(jgen, authorHateoas.getLinks(author));
        jgen.writeEndObject();
    }
}
