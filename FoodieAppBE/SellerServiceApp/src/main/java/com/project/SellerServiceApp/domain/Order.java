package com.project.SellerServiceApp.domain;

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
public class Order
{
private String orderId;
private String UserId;
private String restaurantId,restaurantName;
private String orderedOn,orderStatus;
private List<CartItem> CartItems;
private int totalAmount;
private Address address;
}
