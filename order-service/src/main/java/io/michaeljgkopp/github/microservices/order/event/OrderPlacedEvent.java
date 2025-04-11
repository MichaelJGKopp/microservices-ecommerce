package io.michaeljgkopp.github.microservices.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent { // going to be replaced by class of schema registry, could use a record here atm
    private String orderNumber;
    private String email;
}
