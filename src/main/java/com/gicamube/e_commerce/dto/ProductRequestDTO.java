package com.gicamube.e_commerce.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Min(0)
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;
}
