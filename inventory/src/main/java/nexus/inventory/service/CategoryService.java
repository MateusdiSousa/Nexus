package nexus.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nexus.inventory.dto.CategoryDto;
import nexus.inventory.exceptions.ConflictException;
import nexus.inventory.exceptions.NotFoundException;
import nexus.inventory.model.Category;
import nexus.inventory.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public List<Category> getAllCategory() {
        return repository.findAll();
    }

    public Category getCategoryByCode(String categoryCode) throws NotFoundException {
        Category category = repository.findOneByCategoryCode(categoryCode)
                .orElseThrow(() -> new NotFoundException("Category not found or not exists"));
        return category;
    }

    public Category createCategory(CategoryDto categoryDto) throws ConflictException {
        if (repository.findOneByCategoryCode(categoryDto.code()).isPresent()) {
            throw new ConflictException("Already exists a category with this code");
        }

        Category newCategory = new Category(categoryDto);
        return repository.save(newCategory);
    }

    public Category updateCategory(CategoryDto categoryDto) throws NotFoundException {
        Category category = getCategoryByCode(categoryDto.code());
        category.setName(categoryDto.name());

        return repository.save(category);
    }
}
