package io.michaeljgkopp.github.microservices.order.mapper;

import io.michaeljgkopp.github.microservices.order.dto.OrderRequest;
import io.michaeljgkopp.github.microservices.order.dto.OrderResponse;
import io.michaeljgkopp.github.microservices.order.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        return Order.builder()
                .orderNumber(request.orderNumber())
                .skuCode(UUID.randomUUID().toString())
                .price(request.price())
                .quantity(request.quantity())
                .build();
    }

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .skuCode(UUID.randomUUID().toString())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }
}