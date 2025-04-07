package com.prodmanager.core.config;

import com.prodmanager.core.category.entity.CategoryEntity;
import com.prodmanager.core.product.dto.ProductResponseDto;
import com.prodmanager.core.product.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.Converter;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Convert category.id
        Converter<CategoryEntity, Long> categoryToId = ctx ->
                ctx.getSource() != null ? ctx.getSource().getId() : null;

        // Convert category.name
        Converter<CategoryEntity, String> categoryToName = ctx ->
                ctx.getSource() != null ? ctx.getSource().getName() : null;

        // Mapping principal
        mapper.typeMap(ProductEntity.class, ProductResponseDto.class).addMappings(map -> {
            map.using(categoryToId).map(ProductEntity::getCategory, ProductResponseDto::setCategoryId);
            map.using(categoryToName).map(ProductEntity::getCategory, ProductResponseDto::setCategoryName);
        });

        return mapper;
    }

}
