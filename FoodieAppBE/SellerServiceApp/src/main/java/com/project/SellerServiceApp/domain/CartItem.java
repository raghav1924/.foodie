package com.project.SellerServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private FoodItems foodItem;
    private int quantity;
    private int price;
}
