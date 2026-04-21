package com.gicamube.e_commerce.service;

import com.gicamube.e_commerce.dto.ProductRequestDTO;
import com.gicamube.e_commerce.dto.ProductResponseDTO;
import com.gicamube.e_commerce.mapper.ProductMapper;
import com.gicamube.e_commerce.model.Product;
import com.gicamube.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO dto) {

        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);

        return productMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getAll(
            int page,
            int size,
            String name,
            Sort sort
    ) {

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> products;

        if (name != null && !name.isEmpty()) {
            products = productRepository
                    .findByNameContainingIgnoreCase(name, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(productMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return productMapper.toDTO(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
