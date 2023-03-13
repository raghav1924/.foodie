package com.project.UserServiceApp.services;

import com.project.UserServiceApp.domain.Order;
import com.project.UserServiceApp.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class COrderService implements IOrderService{
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IUserService iUserService;
    @Override
    public Order getOrderDetails(String orderId) {
        return iOrderRepository.findById(orderId).get();
    }

    @Override
    public Order addOrder(Order order) {
        return iOrderRepository.insert(order);
    }


    @Override
    public Order updateOrderStatus(String orderId, String orderStatus)
    {
        Order order =iOrderRepository.findById(orderId).get();
        order.setOrderStatus(orderStatus);
        Order order1=iOrderRepository.save(order);
        iUserService.updateOrderStatusToUser(order1.getUserId(),orderId,orderStatus);
        return order1;
    }
}
