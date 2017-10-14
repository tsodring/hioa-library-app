package library.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import library.common.model.hateoas.ILibraryEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static library.common.utils.Constants.AUTHOR;
import static library.common.utils.Constants.BOOK;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="iSBN" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pkId",
    "title",
    "iSBN",
    "authors"
})
/**
 * Created by tsodring on 9/25/17.
 */
@Entity
@Table(name = "books")
@XmlRootElement(name = BOOK, namespace="http://abi.hioa.no/types/library")
public class Book implements ILibraryEntity {

    @XmlElement()
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false, updatable = false)
    private Long pkId;

    @XmlElement(name="title", required = true)
    @Column(name = "title")
    private String title;

    @XmlElement(name="iSBN", required = true)
    @Column(name = "isbn")
    private String iSBN;

    public Book() {}

    @XmlElementWrapper(name = "authors")
    @XmlElement(name= "author")

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Author> authors = new HashSet<>();

    public Long getPkId() {
    return pkId;
}

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getTitle() {
        return title;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    @JsonIgnore
    public String getBaseTypeName() {
        return BOOK;
    }

}
