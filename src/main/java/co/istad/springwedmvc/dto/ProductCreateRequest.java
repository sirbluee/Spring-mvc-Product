package co.istad.springwedmvc.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCreateRequest(
        @NotBlank(message = "Name is required")
        String name,

        @Positive(message = "Price must be positive")
        @NotNull(message = "Price is required")
        Double price,

        @Positive(message = "Quantity must be positive")
        @NotNull(message = "Quantity is required")
        @Max(value = 100, message = "Quantity must not exceed 100")
        Integer qty
) {}
