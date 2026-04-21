package com.gicamube.e_commerce.repository;

import com.gicamube.e_commerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
