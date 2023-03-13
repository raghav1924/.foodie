package com.project.SellerServiceApp.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchange_name="mail_exchange";
    private String mail_queue_name="mail_queue";
    private String orderStatus_queue_name="orderStatus_queue_sellerService";

    @Bean
    public DirectExchange getExchange()
    {
        return new DirectExchange(exchange_name);
    }

    @Bean
    public Queue mailQueue()
    {
        return new Queue(mail_queue_name);
    }

    @Bean
    public Queue orderStatusQueue()
    {
        return new Queue(orderStatus_queue_name);
    }

    @Bean
    public Jackson2JsonMessageConverter getProducerjsonConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getProducerjsonConverter());
        return rabbitTemplate;
    }

    @Bean
    public Binding mailBinding(Queue mailQueue, DirectExchange d)
    {
        return BindingBuilder.bind(mailQueue).to(d).with("mail_routing");
    }

    @Bean
    public Binding orderStatusBinding(Queue orderStatusQueue, DirectExchange d)
    {
        return BindingBuilder.bind(orderStatusQueue).to(d).with("orderStatus_routing_sellerService");
    }
}

