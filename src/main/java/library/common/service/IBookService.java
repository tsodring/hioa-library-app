package library.common.service;


import library.common.model.Book;

import java.util.List;
import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
public interface IBookService {
    Set getAll();
    Book findOne(Long id);
    Set<Book> findAll();
    Book save(Book book);
    Book update(Long id, Book book) throws Exception;
    void delete(Long id);
    Set<Book> findByISBN(String isbn);
    Set<Book> findByTitle(String title);
}
