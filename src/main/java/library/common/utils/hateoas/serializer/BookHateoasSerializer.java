package library.common.utils.hateoas.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import library.common.model.Book;
import library.common.model.hateoas.ILibraryEntity;
import library.common.utils.hateoas.IHateoasObject;

import java.io.IOException;

import static library.common.utils.Constants.*;

/**
 *
 * Serialise an outgoing Book object as JSON.
 *
 *
 */
public class BookHateoasSerializer extends HateoasSerializer implements IHateoasSerializer {

    @Override
    public void serializeLibraryEntity(ILibraryEntity libraryEntity,
                                     IHateoasObject bookHateoas, JsonGenerator jgen) throws IOException {
        Book book = (Book) libraryEntity;
        jgen.writeStartObject();

        if (book != null && book.getPkId() != null) {
            jgen.writeNumberField(PKID, book.getPkId().longValue());
        }
        if (book != null && book.getTitle() != null) {
            jgen.writeStringField(BOOK_TITLE, book.getTitle());
        }
        if (book != null && book.getiSBN() != null) {
            jgen.writeStringField(BOOK_ISBN, book.getiSBN());
        }
        printHateoasLinks(jgen, bookHateoas.getLinks(book));
        
        jgen.writeEndObject();
    }
}
