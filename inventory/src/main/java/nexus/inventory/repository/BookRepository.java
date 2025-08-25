package nexus.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nexus.inventory.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
