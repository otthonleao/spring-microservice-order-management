package dev.otthon.ordermanagement.controllers;

import dev.otthon.ordermanagement.controllers.dto.ApiResponseDTO;
import dev.otthon.ordermanagement.controllers.dto.OrderResponseDTO;
import dev.otthon.ordermanagement.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponseDTO<OrderResponseDTO>> listOrders(@PathVariable("customerId") Long customerId,
                                                                       @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){


        return ResponseEntity.ok(null);
    }

}
