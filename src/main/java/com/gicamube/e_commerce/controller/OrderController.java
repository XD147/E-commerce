package com.gicamube.e_commerce.controller;

import com.gicamube.e_commerce.dto.OrderRequestDTO;
import com.gicamube.e_commerce.dto.OrderResponseDTO;
import com.gicamube.e_commerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO create(@RequestBody @Valid OrderRequestDTO dto) {
        return orderService.createOrder(dto);
    }
}
