package com.project.SellerServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuisines
{
    private List<FoodItems> foodItems;
    private String cuisineName;
}
