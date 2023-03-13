package com.project.UserServiceApp.rabbitmq;


import com.project.UserServiceApp.domain.Order;
import com.project.UserServiceApp.rabbitmq.mailSender.SendMail;
import com.project.UserServiceApp.services.IOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class Consumer {
    @Autowired
    private SendMail mailSender;
    @Autowired
    private IOrderService iOrderService;



    @RabbitListener(queues="orderStatus_queue_sellerService")
    public void sendMail(Order order)
    {
        System.out.println("Rabbit listener of UserService");
        System.out.println(order);
        iOrderService.updateOrderStatus(order.getOrderId(), order.getOrderStatus());
    }
}
