package com.gicamube.e_commerce.dto;

public record OrderItemResponseDTO(
        Long productId,
        String productName,
        Integer quantity,
        Double price
) {
}
