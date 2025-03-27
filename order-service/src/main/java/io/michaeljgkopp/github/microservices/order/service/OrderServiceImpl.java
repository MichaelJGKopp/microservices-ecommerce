package io.michaeljgkopp.github.microservices.order.service;

import io.michaeljgkopp.github.microservices.order.dto.OrderRequest;
import io.michaeljgkopp.github.microservices.order.dto.OrderResponse;
import io.michaeljgkopp.github.microservices.order.mapper.OrderMapper;
import io.michaeljgkopp.github.microservices.order.model.Order;
import io.michaeljgkopp.github.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        return orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
