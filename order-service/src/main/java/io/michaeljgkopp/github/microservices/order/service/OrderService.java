package io.michaeljgkopp.github.microservices.order.service;

import io.michaeljgkopp.github.microservices.order.dto.OrderRequest;
import io.michaeljgkopp.github.microservices.order.dto.OrderResponse;
import io.michaeljgkopp.github.microservices.order.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrders();
}
