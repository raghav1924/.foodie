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
@Document(collection ="sellerService")
public class Seller
{
    @Id
    private String email;
    private String name,phoneNo;
    private Map<String,String> restaurants;

}
