package io.michaeljgkopp.github.microservices.product.repository;

import io.michaeljgkopp.github.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
