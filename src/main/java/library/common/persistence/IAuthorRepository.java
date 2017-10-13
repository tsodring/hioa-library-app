package library.common.persistence;

import library.common.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IAuthorRepository extends CrudRepository<Author, Long> {

    @Override
    Author save(Author Author);

    @Override
    Set<Author> findAll();

    // authorFirstName
    Set<Author> findByAuthorFirstName(String authorFirstName);

    // authorLastName
    Set<Author> findByAuthorLastName(String authorLastName);
}
