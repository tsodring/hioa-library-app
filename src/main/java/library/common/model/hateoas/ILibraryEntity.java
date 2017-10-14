package library.common.model.hateoas;

import java.io.Serializable;

/**
 * Created by tsodring on 10/13/17.
 */
public interface ILibraryEntity extends Serializable {
    Long getPkId();
    void setPkId(Long id);
    String getBaseTypeName();
}
