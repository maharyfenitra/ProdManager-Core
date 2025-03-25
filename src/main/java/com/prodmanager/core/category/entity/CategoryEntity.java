package com.prodmanager.core.category.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Parent-child relationship: A category can have a parent category
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;

    // Inverse relationship: A category can have multiple subcategories
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> subCategories;

}

