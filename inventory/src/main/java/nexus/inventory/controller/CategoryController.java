package nexus.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexus.inventory.exceptions.ConflictException;
import nexus.inventory.exceptions.NotFoundException;
import nexus.inventory.model.Category;
import nexus.inventory.service.CategoryService;
import nexus.inventory.dto.CategoryDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("api/categories")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = service.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{code}")
    public ResponseEntity<Category> getCategory(@PathVariable("code") Integer code) throws NotFoundException {
        return ResponseEntity.ok(service.getCategoryByCode(code));
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) throws ConflictException {
        Category createdCategory = service.createCategory(categoryDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping()
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDto categoryDto) throws NotFoundException {
        Category updatedCategory = service.updateCategory(categoryDto);

        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("{category_code}")
    public ResponseEntity<String> deleteBook(@PathVariable("category_code") Integer categoryCode) {
        service.deleteCategory(categoryCode);

        return ResponseEntity.ok("Category deleted sucessfully");
    }
}
