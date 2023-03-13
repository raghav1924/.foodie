package com.project.UserAuthApp.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchange_name="service_exchange";
    private String queue_name="mail_queue";

    @Bean
    public DirectExchange getExchange()
    {
        return new DirectExchange(exchange_name);
    }
    @Bean
    public Queue getQueue()
    {
        return new Queue(queue_name);
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
    public Binding getBinding(Queue q, DirectExchange d)
    {
        return BindingBuilder.bind(q).to(d).with("mail_routing");
    }
}
