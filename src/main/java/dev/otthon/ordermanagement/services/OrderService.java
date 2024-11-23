package dev.otthon.ordermanagement.services;

import dev.otthon.ordermanagement.controllers.dto.OrderResponseDTO;
import dev.otthon.ordermanagement.entities.Order;
import dev.otthon.ordermanagement.entities.OrderItem;
import dev.otthon.ordermanagement.listener.dto.OrderCreatedEventDTO;
import dev.otthon.ordermanagement.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public void save(OrderCreatedEventDTO event) {

        var entity = new Order();

        entity.setOrderId(event.getCodigoPedido());
        entity.setCustomerId(event.getCodigoCliente());
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);

    }

    public Page<OrderResponseDTO> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        // Convertendo todos os itens da lista de Order para OrderResponseDTO
        return orders.map(OrderResponseDTO::fromEntity);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
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
