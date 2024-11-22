package dev.otthon.ordermanagement.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private static final String QUEUE_ORDER_CREATED = "order-created";

}
