package library.common.model;

import library.common.model.hateoas.ILibraryEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static library.common.utils.Constants.AUTHOR;

/**
 * Created by tsodring on 9/25/17.
 * <p>
 * Note the base class can be used when creating SOAP, REST and Hateoas objects so has a
 * lot going on it.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "pkId",
        "authorFirstName",
        "authorLastName"
})

@Entity
@Table(name = "authors_table")
@XmlRootElement(name = AUTHOR, namespace = "http://abi.hioa.no/types/library")
public class Author implements ILibraryEntity {

    @XmlElement()
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id", nullable = false, updatable = false)
    private Long pkId;

    @XmlElement(name = "authorFirstName", required = true)
    @Column(name = "author_first_name")
    private String authorFirstName;

    @XmlElement(name = "authorLastName", required = true)
    @Column(name = "author_last_name")
    private String authorLastName;

    @XmlTransient
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    @XmlTransient
    @JsonIgnore
    @Override
    public String getBaseTypeName() {
        return AUTHOR;
    }


    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "pkId=" + pkId +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                '}';
    }
}
