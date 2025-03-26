package io.michaeljgkopp.github.microservices.product.service;

import io.michaeljgkopp.github.microservices.product.dto.ProductRequest;

public interface ProductService {
    void createProduct(ProductRequest productRequest);
}
