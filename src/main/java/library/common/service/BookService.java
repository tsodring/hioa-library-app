package library.common.service;

import library.common.model.Book;
import library.common.persistence.IBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@Service
@Transactional
public class BookService implements IBookService{

    private IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Set getAll() {
        return bookRepository.findAll();
    }

    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Set<Book> findAll() { return bookRepository.findAll(); }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) throws Exception {
        Book originalBook = bookRepository.findOne(id);
        if (originalBook == null){
            throw new Exception("No book exists with Id " + id);
        }
        originalBook.setiSBN(book.getiSBN());
        originalBook.setTitle(book.getTitle());
        return originalBook;
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Set<Book> findByISBN(String isbn) {
        return bookRepository.findByISBN(isbn);
    }

    @Override
    public Set<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
