package dev.otthon.ordermanagement.services;

import dev.otthon.ordermanagement.entities.Order;
import dev.otthon.ordermanagement.entities.OrderItem;
import dev.otthon.ordermanagement.listener.dto.OrderCreatedEventDTO;
import dev.otthon.ordermanagement.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(OrderCreatedEventDTO event) {

        var entity = new Order();

        entity.setOrderId(event.getCodigoPedido());
        entity.setCustomerId(event.getCodigoCliente());
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);

    }

    private BigDecimal getTotal(OrderCreatedEventDTO event) {
        return event.getItens()
                .stream()
                .map(i -> i.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEventDTO event) {
        return event.getItens().stream()
                .map(i -> new OrderItem(i.getProduto(), i.getQuantidade(), i.getPreco()))
                .toList();
    }

}
