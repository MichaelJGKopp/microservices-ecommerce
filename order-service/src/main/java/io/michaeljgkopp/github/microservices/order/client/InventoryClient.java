package io.michaeljgkopp.github.microservices.order.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    Logger logger = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    // @TimeLimiter instead easier to define on RestClient itself in RestClientConfig
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable t) {
        logger.error("Can not get inventory for skuCode {}, failure reason: {}", skuCode, t.getMessage());
        return false;
    }
}
