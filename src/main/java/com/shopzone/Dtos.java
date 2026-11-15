package com.shopzone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Dtos {

    public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
    ) {}

    public record RegisterRequest(
        @NotBlank @Size(min = 3, max = 30) String username,
        @NotBlank @Size(min = 4) String password
    ) {}

    public record OrderRequest(
        @NotBlank String username,
        @Positive double totalAmount,
        @Positive int itemCount
    ) {}

    public record ApiError(
        String code,
        String message // Changed from 'error' to 'message' to match frontend!
    ) {}
}