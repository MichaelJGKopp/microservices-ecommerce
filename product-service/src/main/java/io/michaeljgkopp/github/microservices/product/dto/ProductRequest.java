package io.michaeljgkopp.github.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String id,
        String name,
        String description,
        BigDecimal price) {
}
