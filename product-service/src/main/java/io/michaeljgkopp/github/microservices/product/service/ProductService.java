package io.michaeljgkopp.github.microservices.product.service;

import io.michaeljgkopp.github.microservices.product.dto.ProductRequest;
import io.michaeljgkopp.github.microservices.product.model.Product;

public interface ProductService {
    Product createProduct(ProductRequest productRequest);
}
