package com.project.SellerServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItems
{
    private String itemName,itemPrice,itemDescription;
    @Lob
    private byte[] foodImage;
}
