package library.common.model;

import library.common.model.hateoas.ILibraryEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import static library.common.utils.Constants.AUTHOR;
import static library.common.utils.Constants.BOOK;

/**
 * Created by tsodring on 9/25/17.
 *
 * Note the base class can be used when creating SOAP, REST and Hateoas objects so has a
 * lot going on it.
 *
 * ResourceSupport getId returns a link so the traditional long id is caled pkIk
 */
@Entity
@Table(name = "authors_table")
@XmlRootElement(name = AUTHOR, namespace="http://abi.hioa.no/types/library")
public class Author implements ILibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id", nullable = false, updatable = false)
    private Long pkId;

    @Column(name = "author_first_name")
    private String authorFirstName;

    @Column(name = "author_last_name")
    private String authorLastName;
/*
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
*/
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

    @Override
    public String getBaseTypeName() {
        return AUTHOR;
    }

    /*
        public Set<Book> getBooks() {
            return books;
        }

        public void setBooks(Set<Book> books) {
            this.books = books;
        }
    */
    @Override
    public String toString() {
        return "Author{" +
                "pkId=" + pkId +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                '}';
    }
}
