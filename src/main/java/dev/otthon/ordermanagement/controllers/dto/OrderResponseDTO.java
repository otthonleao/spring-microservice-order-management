package dev.otthon.ordermanagement.controllers.dto;

import java.math.BigDecimal;

public record OrderResponseDTO(Long orderId,
                               Long customerId,
                               BigDecimal total) {
}
