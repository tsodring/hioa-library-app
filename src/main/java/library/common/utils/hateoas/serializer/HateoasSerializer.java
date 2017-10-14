package library.common.utils.hateoas.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import library.common.model.hateoas.ILibraryEntity;
import library.common.utils.hateoas.IHateoasObject;
import library.common.utils.hateoas.Link;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import static library.common.utils.Constants.*;


/**
 * Created by tsodring on 2/9/17.
 */
public class HateoasSerializer extends StdSerializer<IHateoasObject> {

    public HateoasSerializer() {
        super(IHateoasObject.class);
    }

    @Override
    public void serialize(IHateoasObject hateoasObject, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        // For lists the output should be
        //  { "entity": [], "_links": [] }
        // An empty list should produce
        // { "entity": [], "_links": [] }
        // An entity should produce
        // { "field" : "value", "_links": [] }
        // No such thing as an empty entity
        List<ILibraryEntity> list = hateoasObject.getList();
        if (list.size() > 0) {
            if (!hateoasObject.isSingleEntity()) {
                jgen.writeStartObject();

                jgen.writeStartArray();
            }
            for (ILibraryEntity entity : list) {
                serializeLibraryEntity(entity, hateoasObject, jgen);
            }
            if (!hateoasObject.isSingleEntity()) {
                jgen.writeEndArray();
                printHateoasLinks(jgen, hateoasObject.getSelfLinks());
                jgen.writeEndObject();
            }
        }
        // It's an empty object, so just returning Hateoas self links
        else {
            jgen.writeStartObject();
            jgen.writeStartArray();
            jgen.writeEndArray();
            printHateoasLinks(jgen, hateoasObject.getSelfLinks());
            jgen.writeEndObject();
        }
    }

    protected void serializeLibraryEntity(ILibraryEntity entity, IHateoasObject hateoasObject,
                                        JsonGenerator jgen) throws IOException {
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        throw new UnsupportedOperationException();
    }


    public static void printHateoasLinks(JsonGenerator jgen, Set<Link> links) throws IOException {

        if (links != null && links.size() > 0) {
            jgen.writeArrayFieldStart(LINKS);
            for (Link link : links) {
                jgen.writeStartObject(link.getLinkName());
                jgen.writeStringField(HREF, link.getHref());
                jgen.writeStringField(REL, link.getRel());
                jgen.writeBooleanField(TEMPLATED, link.getTemplated());
                jgen.writeEndObject();
            }
            jgen.writeEndArray();
        } else {
            jgen.writeArrayFieldStart(LINKS);
            jgen.writeEndArray();
        }
    }
}
