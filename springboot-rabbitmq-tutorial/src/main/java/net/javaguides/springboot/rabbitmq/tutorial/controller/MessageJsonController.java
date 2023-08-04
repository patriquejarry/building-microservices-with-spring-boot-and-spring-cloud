package net.javaguides.springboot.rabbitmq.tutorial.controller;

import net.javaguides.springboot.rabbitmq.tutorial.dto.User;
import net.javaguides.springboot.rabbitmq.tutorial.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonProducer rabbitMQProducer;

    public MessageJsonController(RabbitMQJsonProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    // http://localhost:8080/api/v1/publish?message=hello
    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user){
        rabbitMQProducer.sendMessage(user);
        return ResponseEntity.ok("Message Json User sent to RabbitMQ Producer : " + user);
    }
}
