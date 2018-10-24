package org.snow.config;

import org.springframework.amqp.core.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "rabbitMqConfig")
public class RabbitMqConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("client");
    }

    @Bean
    public Queue myQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding myBinding(DirectExchange directExchange, Queue myQueue) {
        return BindingBuilder.bind(myQueue).to(directExchange).with("alarm");
    }
}
