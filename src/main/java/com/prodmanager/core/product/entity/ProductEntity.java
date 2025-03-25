package com.prodmanager.core.product.entity;

import com.prodmanager.core.category.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(name = "created_at", updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    // Relationship with CategoryEntity (A product belongs to one category)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true) // Category ID will be stored here
    private CategoryEntity category;

    @PrePersist
    public void prePersist() {
        createdAt = java.time.LocalDateTime.now(); // Set the created timestamp before inserting
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = java.time.LocalDateTime.now(); // Update the timestamp before updating
    }
}
