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
    @Query("SELECT DISTINCT b FROM book b WHERE " +
            "LOWER(b.title) LIKE %:term% OR " +
            "LOWER(b.publisher) LIKE %:term% OR " +
            "LOWER(b.author) LIKE %:term%")
    List<Book> searchBooks(@Param("term") String term);

    List<Book> findByBookCategoryIn(Set<Category> bookCategory);

    Optional<Book> findByBookCode(Long bookCode);

    void deleteByBookCode(Long bookCode);
}
