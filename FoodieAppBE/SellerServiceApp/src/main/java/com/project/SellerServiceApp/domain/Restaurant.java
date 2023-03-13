package com.project.SellerServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Lob;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "restaurantService")
public class Restaurant
{
    @Id
    private String restaurantId;
    private  String restaurantName;
    @Lob
    private byte[] restaurantImage;
    private List<Cuisines> menu;
    private Address address;
    private String sellerEmail;
    private List<Order> orders;
}
