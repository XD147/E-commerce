package com.gicamube.e_commerce.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    @NotNull
    private Long userId;

    @NotEmpty
    private List<OrderItemRequestDTO> items;
}
