package net.javaguides.springboot.kafka.microservices.orderservice.controller;

import net.javaguides.springboot.kafka.microservices.orderservice.kafka.OrderProducer;
import net.javaguides.springboot.kafka.microservices.basedomains.dto.Order;
import net.javaguides.springboot.kafka.microservices.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody Order order){

        LOGGER.info(String.format("Order : %s", order));

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("New order in PENDING status");
        orderEvent.setStatus("PENDING");
        orderEvent.setOrder(order);
        orderProducer.sendMessage(orderEvent);

        return ResponseEntity.ok("OrderEvent sent to Kafka Producer : " + orderEvent);
    }
}
