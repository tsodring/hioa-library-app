//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.10.08 at 09:17:31 PM CEST 
//


package library.common.model.soap;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="bookList" type="{http://abi.hioa.no/types/library}bookList"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "AuthorFindAllRequest")
public class AuthorFindAllRequest {

    @XmlElement(required = true)
    protected AuthorList bookList;

    /**
     * Gets the value of the bookList property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorList }
     *     
     */
    public AuthorList getAuthorList() {
        return bookList;
    }

    /**
     * Sets the value of the bookList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorList }
     *     
     */
    public void setAuthorList(AuthorList value) {
        this.bookList = value;
    }

}
