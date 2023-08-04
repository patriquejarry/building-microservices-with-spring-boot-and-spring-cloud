package net.javaguides.springboot.rabbitmq.microservices.orderservice.publisher;

import net.javaguides.springboot.rabbitmq.microservices.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingkey.stock.name}")
    private String routingKeyStock;

    @Value("${rabbitmq.routingkey.email.name}")
    private String routingKeyEmail;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent) {
        LOGGER.info("OrderEvent sent to Stock Queue : " + orderEvent);
        rabbitTemplate.convertAndSend(exchange, routingKeyStock, orderEvent);

        LOGGER.info("OrderEvent sent to Email Queue : " + orderEvent);
        rabbitTemplate.convertAndSend(exchange, routingKeyEmail, orderEvent);
    }
}
