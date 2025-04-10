package io.michaeljgkopp.github.microservices.inventory.controller;

import io.michaeljgkopp.github.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        System.out.println("Checking inventory for SKU: " + skuCode + " with quantity: " + quantity);
//        try {
//            Thread.sleep(5_000); // Simulate a delay for testing
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return inventoryService.isInStock(skuCode, quantity);
    }
}
