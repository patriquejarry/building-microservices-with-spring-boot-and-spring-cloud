package net.javaguides.springboot.kafka.tutorial.controller;

import net.javaguides.springboot.kafka.tutorial.publisher.KafkaJsonProducer;
import net.javaguides.springboot.kafka.tutorial.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private KafkaJsonProducer kafkaJsonProducer;

    public MessageJsonController(KafkaJsonProducer kafkaJsonProducer) {
        this.kafkaJsonProducer = kafkaJsonProducer;
    }

    // http://localhost:8080/api/v1/publish
    // { "id": 1, "firstName": "Patrique", "lastName": "Jarry" }
    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
        kafkaJsonProducer.sendMessage(user);
        return ResponseEntity.ok("Message JSON sent to Kafka Producer : " + user);
    }
}
