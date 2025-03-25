package com.prodmanager.core.product.service;

import com.prodmanager.core.product.entity.ProductEntity;
import com.prodmanager.core.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Annotations qui indique que c'est un service Spring
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired  // Injection de dépendance du repository
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Récupérer tous les produits
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // Récupérer un produit par son ID
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Sauvegarder un produit (ajouter ou mettre à jour)
    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    // Supprimer un produit par son ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
