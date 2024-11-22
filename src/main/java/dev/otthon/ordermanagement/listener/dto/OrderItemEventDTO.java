package dev.otthon.ordermanagement.listener.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEventDTO {

    private String produto;
    private Integer quantidade;
    private BigDecimal preco;

}
