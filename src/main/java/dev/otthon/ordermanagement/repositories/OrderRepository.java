package dev.otthon.ordermanagement.repositories;

import dev.otthon.ordermanagement.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
