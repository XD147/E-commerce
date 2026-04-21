package com.gicamube.e_commerce.dto;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock
) {
}
