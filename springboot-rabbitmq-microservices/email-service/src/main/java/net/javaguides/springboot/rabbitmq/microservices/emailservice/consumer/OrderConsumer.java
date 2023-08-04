package net.javaguides.springboot.rabbitmq.microservices.emailservice.consumer;

import net.javaguides.springboot.rabbitmq.microservices.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void consume(OrderEvent orderEvent) {
        LOGGER.info("OrderEvent received : " + orderEvent);

        // Get order and send mail
    }
}
