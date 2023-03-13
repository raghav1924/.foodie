package com.project.UserServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orderDetails")
public class Order
{
@Id
private String orderId;
private String UserId;
private String restaurantId,restaurantName;
private String orderedOn,orderStatus;
private List<CartItem> CartItems;
private int totalAmount;
private Address address;
}
