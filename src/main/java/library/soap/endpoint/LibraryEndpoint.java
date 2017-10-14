package library.soap.endpoint;

import library.common.model.Author;
import library.common.model.Book;
import library.common.model.soap.BookCreateRequest;
import library.common.model.soap.BookCreateResponse;
import library.common.model.soap.*;
import library.common.service.IAuthorService;
import library.common.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Note the code deliberately does not handle exceptions properly. This is to expose students to
 * the need to have proper exception handling
 */
@Endpoint
public class LibraryEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryEndpoint.class);
    private static final String NAMESPACE_URI = "http://abi.hioa.no/types/library";
    private IBookService bookService;
    private IAuthorService authorService;

    public LibraryEndpoint(IBookService bookService, IAuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /* All book endpoints */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookCreateRequest")
    @ResponsePayload
    public BookCreateResponse createBook(@RequestPayload BookCreateRequest incomingPayload) {
        Book incoming = incomingPayload.getBook();
        LOGGER.info("Soap endpoint received createBook request [ISBN={}, title={}]", incoming.getiSBN(),
                incoming.getTitle());
        BookCreateResponse response = new BookCreateResponse();
        response.setBook(bookService.save(incoming));
        LOGGER.info("Soap endpoint sending book response='{}'", response.getBook().toString());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookUpdateRequest")
    @ResponsePayload
    public Book updateBook(@RequestPayload BookUpdateRequest incomingPayload) throws Exception {
        Book incoming = incomingPayload.getBook();
        LOGGER.info("Soap endpoint received updateBook request [ISBN={}, title={}]", incoming.getiSBN(),
                incoming.getTitle());
        Book outgoing = bookService.update(incoming.getPkId(), incoming);
        LOGGER.info("Soap endpoint sending book response='{}'", outgoing.toString());
        return outgoing;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookDeleteRequest")
    @ResponsePayload
    public BookDeleteResponse deleteBook(@RequestPayload BookDeleteRequest deleteRequest) throws Exception {
        Long pkId = deleteRequest.getPkId();
        LOGGER.info("Soap endpoint received deleteBook request [pkId={}]", pkId);
        // Just hardcoded prevention of deletion of inserted books
        // so there will always be something in a findAll
        if (pkId < 3) {
            throw new Exception("You cannot delete the orignal books, only other books");
        }
        bookService.delete(pkId);
        BookDeleteResponse response = new BookDeleteResponse();
        response.setResponse("Deleted book with id " + pkId);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookFindOneRequest")
    @ResponsePayload
    public Book getBook(@RequestPayload BookFindOneRequest request) {
        Long pkId = request.getPkId();
        LOGGER.info("Soap endpoint received getBook request [pkId={}]", pkId);
        return bookService.findOne(pkId);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookFindAllRequest")
    @ResponsePayload
    public BookFindAllResponse getBooks(@RequestPayload BookFindAllRequest request) {
        LOGGER.info("Soap endpoint received findAllBooks request");
        BookFindAllResponse response = new BookFindAllResponse();
        BookList bookList = new BookList();
        bookList.setBooks(bookService.findAll());
        response.setBookList(bookList);
        return response;
    }

    /* All author endpoints */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthorCreateRequest")
    @ResponsePayload
    public AuthorCreateResponse createAuthor(@RequestPayload AuthorCreateRequest incomingPayload) {
        Author incoming = incomingPayload.getAuthor();
        LOGGER.info("Soap endpoint received createAuthor request [firstname={}, lastname={}]",
                incoming.getAuthorFirstName(), incoming.getAuthorLastName());
        AuthorCreateResponse response = new AuthorCreateResponse();
        response.setAuthor(authorService.save(incoming));
        LOGGER.info("Soap endpoint sending author response='{}'", response.getAuthor().toString());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthorUpdateRequest")
    @ResponsePayload
    public Author updateAuthor(@RequestPayload AuthorUpdateRequest incomingPayload) throws Exception {
        Author incoming = incomingPayload.getAuthor();
        LOGGER.info("Soap endpoint received updateAuthor request [firstname={}, lastname={}]",
                incoming.getAuthorFirstName(), incoming.getAuthorLastName());
        Author outgoing = authorService.update(incoming.getPkId(), incoming);
        LOGGER.info("Soap endpoint sending author response='{}'", outgoing.toString());
        return outgoing;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthorDeleteRequest")
    @ResponsePayload
    public AuthorDeleteResponse deleteAuthor(@RequestPayload AuthorDeleteRequest deleteRequest) throws Exception {
        Long pkId = deleteRequest.getPkId();
        LOGGER.info("Soap endpoint received deleteAuthor request [pkId={}]", pkId);
        // Just hardcoded prevention of deletion of inserted authors
        // so there will always be something in a findAll
        if (pkId < 3) {
            throw new Exception("You cannot delete the orignal authors, only other authors");
        }
        authorService.delete(pkId);
        AuthorDeleteResponse response = new AuthorDeleteResponse();
        response.setResponse("Deleted author with id " + pkId);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthorFindOneRequest")
    @ResponsePayload
    public Author getAuthor(@RequestPayload AuthorFindOneRequest request) {
        Long pkId = request.getPkId();
        LOGGER.info("Soap endpoint received getAuthor request [pkId={}]", pkId);
        return authorService.findOne(pkId);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthorFindAllRequest")
    @ResponsePayload
    public AuthorFindAllResponse getAuthors(@RequestPayload AuthorFindAllRequest request) {
        LOGGER.info("Soap endpoint received findAllAuthors request");
        AuthorFindAllResponse response = new AuthorFindAllResponse();
        AuthorList authorList = new AuthorList();
        authorList.setAuthors(authorService.findAll());
        response.setAuthorList(authorList);
        return response;
    }
}
