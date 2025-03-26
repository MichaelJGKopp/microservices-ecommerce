package io.michaeljgkopp.github.microservices.order.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponse(
        Long id,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
