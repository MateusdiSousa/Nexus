package nexus.inventory.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nexus.inventory.model.Category;
import java.util.List;



public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findOneByCategoryCode(String categoryCode);
    List<Category> findByCategoryCodeIn(List<String> codes);

}
