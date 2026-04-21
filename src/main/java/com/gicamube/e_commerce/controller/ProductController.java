package com.gicamube.e_commerce.controller;

import com.gicamube.e_commerce.dto.ProductRequestDTO;
import com.gicamube.e_commerce.dto.ProductResponseDTO;
import com.gicamube.e_commerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ProductResponseDTO create(@RequestBody @Valid ProductRequestDTO dto){
        return service.create(dto);
    }

    @GetMapping
    public Page<ProductResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return service.getAll(page, size, name, sort);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
