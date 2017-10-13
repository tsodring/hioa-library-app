package library.common.persistence;

import library.common.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

    @Override
    Book save(Book Book);

    @Override
    Set<Book> findAll();

    // bookFirstName
    Set<Book> findByISBN(String isbn);

    // bookLastName
    Set<Book> findByTitle(String title);
}
