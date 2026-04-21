package com.gicamube.e_commerce.mapper;

import com.gicamube.e_commerce.dto.OrderItemResponseDTO;
import com.gicamube.e_commerce.dto.OrderResponseDTO;
import com.gicamube.e_commerce.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponseDTO toDTO(Order order) {

        List<OrderItemResponseDTO> items = order.getItems().stream()
                .map(item -> new OrderItemResponseDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();

        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getTotal(),
                order.getCreatedAt(),
                items
        );
    }
}
