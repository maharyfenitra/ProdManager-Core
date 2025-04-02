package com.prodmanager.core.category.service;

import com.prodmanager.core.category.dto.CategoryRequestDto;
import com.prodmanager.core.category.dto.CategoryResponseDto;
import com.prodmanager.core.category.entity.CategoryEntity;
import com.prodmanager.core.category.repository.CategoryRepository;
import com.prodmanager.core.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto updatedCategoryRequest) {
        // Vérifier si la catégorie existe
        CategoryEntity existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Si la catégorie n'existe pas, lancer une exception ou gérer l'erreur
        if (existingCategory == null) {
            throw new EntityNotFoundException("Category with id " + id + " not found.");
        }

        // Mettre à jour les champs de l'entité avec les données du DTO
        if (updatedCategoryRequest.getName() != null) {
            existingCategory.setName(updatedCategoryRequest.getName());
            existingCategory.setDescription(updatedCategoryRequest.getDescription());
        }

        if (updatedCategoryRequest.getParentId() != null) {
            Optional<CategoryEntity> parentCategoryOpt = categoryRepository.findById(updatedCategoryRequest.getParentId());
            if (parentCategoryOpt.isPresent()) {
                existingCategory.setParentCategory(parentCategoryOpt.get());
            } else {
                // Gérer le cas où le parent n'existe pas (optionnel)
                throw new EntityNotFoundException("Parent category with id " + updatedCategoryRequest.getParentId() + " not found.");
            }
        }

        // Enregistrer l'entité mise à jour dans la base de données
        CategoryEntity updatedCategoryEntity = categoryRepository.save(existingCategory);

        // Mapper l'entité mise à jour en DTO et le retourner
        return modelMapper.map(updatedCategoryEntity, CategoryResponseDto.class);
    }

    // Get all categories
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    // Get a category by ID
    public CategoryResponseDto getCategoryById(Long id) {

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);

        if (category.getParentCategory() != null) {
            categoryResponseDto.setParentId(category.getParentCategory().getId());
        }

        return categoryResponseDto;
    }

    // Delete a category by ID
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
