package library.common.service;

import library.common.model.Author;
import library.common.persistence.IAuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by tsodring on 9/25/17.
 */
@Service
@Transactional
public class AuthorService implements IAuthorService {

    private IAuthorRepository authorRepository;

    public AuthorService(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Set<Author> findAll() {
        Set<Author> authors = authorRepository.findAll();
        return authors;
    }

    @Override
    public Author findOne(Long id) {
        return authorRepository.findOne(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author author) throws Exception {
        Author originalAuthor = authorRepository.findOne(id);
        if (originalAuthor == null){
            throw new Exception("No author exists with Id " + id);
        }
        originalAuthor.setAuthorFirstName(author.getAuthorFirstName());
        originalAuthor.setAuthorLastName(author.getAuthorFirstName());
        return originalAuthor;
    }
    
    @Override
    public void delete(Long id) {
        authorRepository.delete(id);
    }

    @Override
    public Set<Author> findByAuthorFirstName(String authorFirstName) {
        return authorRepository.findByAuthorFirstName(authorFirstName);
    }

    @Override
    public Set<Author> findByAuthorLastName(String authorLastName) {
        return authorRepository.findByAuthorLastName(authorLastName);
    }
}
