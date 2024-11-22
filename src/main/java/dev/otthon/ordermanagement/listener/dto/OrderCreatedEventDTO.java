package dev.otthon.ordermanagement.listener.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEventDTO {

    private Long codigoPedido;
    private Long codigoCliente;
    private List<OrderItemEventDTO> itens;

}
