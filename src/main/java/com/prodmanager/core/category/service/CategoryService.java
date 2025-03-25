package com.prodmanager.core.category.service;

import com.prodmanager.core.category.dto.CategoryRequestDto;
import com.prodmanager.core.category.dto.CategoryResponseDto;
import com.prodmanager.core.category.entity.CategoryEntity;
import com.prodmanager.core.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    // Create a new category
    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto) {
        // Convert the DTO to Entity using ModelMapper
        CategoryEntity categoryEntity = modelMapper.map(categoryRequestDto, CategoryEntity.class);

        // Set the parent category if the parentId is provided
        Optional.ofNullable(categoryRequestDto.getParentId())
                .flatMap(parentId -> categoryRepository.findById(parentId))
                .ifPresent(parentCategory -> {
                    categoryEntity.setParentCategory(parentCategory);
                    System.out.println(parentCategory.getId());
                });

        // Save the category and return the mapped DTO in one step
        return modelMapper.map(categoryRepository.save(categoryEntity), CategoryResponseDto.class);
    }

    // Get all categories
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get a category by ID
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Update an existing category
    public CategoryEntity updateCategory(Long id, CategoryEntity updatedCategory) {
        CategoryEntity existingCategory = getCategoryById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setParentCategory(updatedCategory.getParentCategory());
        return categoryRepository.save(existingCategory);
    }

    // Delete a category by ID
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
