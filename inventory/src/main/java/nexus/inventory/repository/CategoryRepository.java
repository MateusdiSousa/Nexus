package nexus.inventory.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import nexus.inventory.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
