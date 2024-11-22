package dev.otthon.ordermanagement.listener;

import dev.otthon.ordermanagement.listener.dto.OrderCreatedEventDTO;
import dev.otthon.ordermanagement.services.OrderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static dev.otthon.ordermanagement.config.RabbitMqConfig.QUEUE_ORDER_CREATED;

@AllArgsConstructor
@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    @RabbitListener(queues = QUEUE_ORDER_CREATED)
    public void listen(Message<OrderCreatedEventDTO> message){
        logger.info("Message consumed: {}", message);

        orderService.save(message.getPayload());
    }
}
