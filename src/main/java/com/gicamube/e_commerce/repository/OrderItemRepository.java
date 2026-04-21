package com.gicamube.e_commerce.repository;

import com.gicamube.e_commerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
