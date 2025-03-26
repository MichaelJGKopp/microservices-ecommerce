package io.michaeljgkopp.github.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")    // mongodb document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id // of type String !
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
