package com.project.UserServiceApp.contoller;

import com.project.UserServiceApp.domain.Order;
import com.project.UserServiceApp.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderService/")
public class COrderController {
    @Autowired
    private IOrderService iOrderService;


    @GetMapping("getOrderDetails/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String orderId){
        return new ResponseEntity<>(iOrderService.getOrderDetails(orderId), HttpStatus.OK);
    }
    @PostMapping("addOrder")
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        return new ResponseEntity<>(iOrderService.addOrder(order), HttpStatus.OK);

    }
    @PutMapping("updateOrderStatus/{orderId}/{orderStatus}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String orderId,@PathVariable String orderStatus){
        return new ResponseEntity<>(iOrderService.updateOrderStatus(orderId,orderStatus), HttpStatus.OK);

    }
}
