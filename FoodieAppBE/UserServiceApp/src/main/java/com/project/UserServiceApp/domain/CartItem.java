package com.project.UserServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private FoodItems foodItem;
    private int quantity;
    private int price;
}
