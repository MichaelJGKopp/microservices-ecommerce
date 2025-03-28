package io.michaeljgkopp.github.microservices.order.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory", url="localhost:8082")
public interface InventoryServiceProxy {

    @GetMapping("/api/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
