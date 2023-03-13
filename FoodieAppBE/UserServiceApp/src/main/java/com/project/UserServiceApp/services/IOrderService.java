package com.project.UserServiceApp.services;

import com.project.UserServiceApp.domain.Order;

public interface IOrderService {
    public Order getOrderDetails(String orderId);
    public Order addOrder(Order order);
    public Order updateOrderStatus(String orderId,String orderStatus);

}
