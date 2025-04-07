package com.prodmanager.core.product.service;

import com.prodmanager.core.product.dto.ProductResponseDto;
import com.prodmanager.core.product.entity.ProductEntity;
import com.prodmanager.core.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.PropertyMap;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {

        Page<ProductEntity> products=productRepository.findAll(pageable);

        return products.map(product ->
                modelMapper.map(product, ProductResponseDto.class)
        );
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
