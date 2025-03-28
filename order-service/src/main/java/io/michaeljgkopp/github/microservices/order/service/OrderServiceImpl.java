package io.michaeljgkopp.github.microservices.order.service;

import io.michaeljgkopp.github.microservices.order.dto.OrderRequest;
import io.michaeljgkopp.github.microservices.order.dto.OrderResponse;
import io.michaeljgkopp.github.microservices.order.mapper.OrderMapper;
import io.michaeljgkopp.github.microservices.order.model.Order;
import io.michaeljgkopp.github.microservices.order.proxy.InventoryServiceProxy;
import io.michaeljgkopp.github.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryServiceProxy inventoryServiceProxy;


    @Transactional
    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        if (!inventoryServiceProxy.isInStock(order.getSkuCode(), order.getQuantity())) {
            log.error("Not enough inventory, quantity for SKU: {} smaller than {}",
                    order.getSkuCode(), order.getQuantity());
            throw new RuntimeException( // ToDo: Create a custom exception with ResponseStatus
                    "Not enough inventory, quantity for SKU: %s smaller than %d"
                    .formatted(order.getSkuCode(), order.getQuantity()));
        }
        return orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
