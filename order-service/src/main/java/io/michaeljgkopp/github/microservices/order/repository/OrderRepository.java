package io.michaeljgkopp.github.microservices.order.repository;

import io.michaeljgkopp.github.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
