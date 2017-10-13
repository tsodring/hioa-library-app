package library.common.service;

import library.common.model.Author;

import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
public interface IAuthorService {
    Set findAll();
    Author findOne(Long id);
    Author save(Author author);
    Author update(Author author) throws Exception;
    void delete(Long id);
    // authorFirstName
    Set<Author> findByAuthorFirstName(String authorFirstName);
    // authorLastName
    Set<Author> findByAuthorLastName(String authorLastName);
}
