package com.project.UserServiceApp.rabbitmq;

import com.project.UserServiceApp.domain.Order;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    public void sendMailDtoToQueue(EmailDTO emailDTO)
    {
        rabbitTemplate.convertAndSend(directExchange.getName(),"mail_routing",emailDTO);
    }
    public void orderStatusToQueue(Order order)
    {
        rabbitTemplate.convertAndSend(directExchange.getName(),"orderStatus_routing_userService",order);
    }
}
