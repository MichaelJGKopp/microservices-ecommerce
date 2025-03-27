package io.michaeljgkopp.github.microservices.inventory.service;

import io.michaeljgkopp.github.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isInStock(String skuCode, Integer quantity) {
        boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
        log.info("Product with skuCode {} still has {} or more left in stock: {}", skuCode, quantity, isInStock);
        return isInStock;
    }
}
