package com.gicamube.e_commerce.service;

import com.gicamube.e_commerce.dto.*;
import com.gicamube.e_commerce.mapper.OrderMapper;
import com.gicamube.e_commerce.model.*;
import com.gicamube.e_commerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (OrderItemRequestDTO itemDTO : dto.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            /// VALIDACIÓN CLAVE
            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("No hay suficiente stock: " + product.getName());
            }

            /// DESCONTAR STOCK
            product.setStock(product.getStock() - itemDTO.getQuantity());

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());

            total += product.getPrice() * itemDTO.getQuantity();

            items.add(item);
        }

        order.setItems(items);
        order.setTotal(total);

        Order saved = orderRepository.save(order);

        return orderMapper.toDTO(saved);
    }
}
