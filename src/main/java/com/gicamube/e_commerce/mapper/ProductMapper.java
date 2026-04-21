package com.gicamube.e_commerce.mapper;

import com.gicamube.e_commerce.dto.ProductRequestDTO;
import com.gicamube.e_commerce.dto.ProductResponseDTO;
import com.gicamube.e_commerce.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductResponseDTO toDTO(Product product){
        if(product == null) return null;

        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock()
        );
    }

    public Product toEntity(ProductRequestDTO requestDTO){
        if(requestDTO == null) return null;

        return Product.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .price(requestDTO.getPrice())
                .stock(requestDTO.getStock())
                .build();
    }
}
