package com.gicamube.e_commerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        Long userId,
        Double total,
        LocalDateTime createdAt,
        List<OrderItemResponseDTO> items
) {
}
