package dev.otthon.ordermanagement.controllers.dto;

import dev.otthon.ordermanagement.entities.Order;

import java.math.BigDecimal;

public record OrderResponseDTO(Long orderId,
                               Long customerId,
                               BigDecimal total) {

    public static OrderResponseDTO fromEntity(Order entity) {
        return new OrderResponseDTO(entity.getOrderId(),
                                    entity.getCustomerId(),
                                    entity.getTotal()
        );
    }
}
