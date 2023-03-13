package com.project.SellerServiceApp.rabbitmq;


import com.project.SellerServiceApp.domain.Order;
import com.project.SellerServiceApp.services.IRestaurantService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class Consumer {

    @Autowired
    private IRestaurantService iRestaurantService;


    @RabbitListener(queues="orderStatus_queue_userService")
    public void addOrders(Order order)
    {
        System.out.println("Rabbit listener of SellerService");
        System.out.println(order);
        iRestaurantService.addOrderToRestaurant(order);
    }
}
