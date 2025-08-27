package nexus.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nexus.inventory.model.Book;
import nexus.inventory.model.Category;
import java.util.Set;


public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT DISTINCT b FROM Book b " +
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(b.autor) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(b.publisher) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Book> findByTitleOrAutorOrPublisherContaining(@Param("term") String term);

    List<Book> findByBookCategory(Set<Category> bookCategory);

    Optional<Book> findByBookCode(Long bookCode);
}
