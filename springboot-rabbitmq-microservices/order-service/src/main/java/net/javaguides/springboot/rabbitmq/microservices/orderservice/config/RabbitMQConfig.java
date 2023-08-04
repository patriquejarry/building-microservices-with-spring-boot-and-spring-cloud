package net.javaguides.springboot.rabbitmq.microservices.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.stock.name}")
    private String queueStock;

    @Value("${rabbitmq.routingkey.stock.name}")
    private String routingKeyStock;

    @Value("${rabbitmq.queue.email.name}")
    private String queueEmail;

    @Value("${rabbitmq.routingkey.email.name}")
    private String routingKeyEmail;

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue queueStock(){
        return new Queue(queueStock);
    }

    @Bean
    public Queue queueEmail(){
        return new Queue(queueEmail);
    }

    @Bean
    public Binding bindingStock(){
        return BindingBuilder
                .bind(queueStock())
                .to(exchange())
                .with(routingKeyStock);
    }

    @Bean
    public Binding bindingEmail(){
        return BindingBuilder
                .bind(queueEmail())
                .to(exchange())
                .with(routingKeyEmail);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
