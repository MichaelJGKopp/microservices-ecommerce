package io.michaeljgkopp.github.microservices.product.service;

import io.michaeljgkopp.github.microservices.product.dto.ProductRequest;
import io.michaeljgkopp.github.microservices.product.dto.ProductResponse;
import io.michaeljgkopp.github.microservices.product.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        productRepository.save(product);
        log.info("Product created successfully: {}", product);
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
