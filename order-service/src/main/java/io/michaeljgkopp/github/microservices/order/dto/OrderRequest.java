package io.michaeljgkopp.github.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(
        Long id,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity,
        UserDetails userDetails // coming from the ui/auth service
) {
    public record UserDetails(String email, String firstName, String lastName) {}
}
