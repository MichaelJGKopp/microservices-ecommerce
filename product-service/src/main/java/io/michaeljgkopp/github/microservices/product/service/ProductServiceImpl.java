package io.michaeljgkopp.github.microservices.product.service;

import io.michaeljgkopp.github.microservices.product.dto.ProductRequest;
import io.michaeljgkopp.github.microservices.product.model.Product;
import io.michaeljgkopp.github.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest productRequest) {
        // Create product entity
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        // Save product to repository
        productRepository.save(product);
        log.info("Product created successfully: {}", product);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
