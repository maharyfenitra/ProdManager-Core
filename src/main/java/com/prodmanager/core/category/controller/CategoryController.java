package com.prodmanager.core.category.controller;

import com.prodmanager.core.category.dto.CategoryRequestDto;
import com.prodmanager.core.category.dto.CategoryResponseDto;
import com.prodmanager.core.category.entity.CategoryEntity;
import com.prodmanager.core.category.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<Page<CategoryResponseDto>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(categoryService.getAllCategories(
                PageRequest.of(page, size)
        ));
    }

    // Get a category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
