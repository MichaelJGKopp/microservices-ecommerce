package io.michaeljgkopp.github.microservices.product.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
         String id,
         String name,
         String description,
         String skuCode,
         BigDecimal price
) {
}
