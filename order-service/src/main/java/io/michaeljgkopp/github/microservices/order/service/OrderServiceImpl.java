package io.michaeljgkopp.github.microservices.order.service;

import io.michaeljgkopp.github.microservices.order.dto.OrderRequest;
import io.michaeljgkopp.github.microservices.order.dto.OrderResponse;
import io.michaeljgkopp.github.microservices.order.event.OrderPlacedEvent;
import io.michaeljgkopp.github.microservices.order.mapper.OrderMapper;
import io.michaeljgkopp.github.microservices.order.model.Order;
import io.michaeljgkopp.github.microservices.order.client.InventoryClient;
import io.michaeljgkopp.github.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;    // key: Topic name, value: Event


    @Transactional
    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        if (!inventoryClient.isInStock(order.getSkuCode(), order.getQuantity())) {
            log.error("Not enough inventory, quantity for SKU: {} smaller than {}",
                    order.getSkuCode(), order.getQuantity());
            throw new RuntimeException( // ToDo: Create a custom exception with ResponseStatus
                    "Not enough inventory, quantity for SKU: %s smaller than %d"
                    .formatted(order.getSkuCode(), order.getQuantity()));
        }
        order = orderRepository.save(order);

        // send the message to Kafka Topic -> email service, sending out email

        // Create OrderPlacedEvent
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
        orderPlacedEvent.setOrderNumber(order.getOrderNumber());
        orderPlacedEvent.setEmail(orderRequest.userDetails().email());
        orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
        orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());

        log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
        kafkaTemplate.send("order-placed", orderPlacedEvent);
        log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);

        return order;
    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
