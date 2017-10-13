package library.common.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

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
public class Author {

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
