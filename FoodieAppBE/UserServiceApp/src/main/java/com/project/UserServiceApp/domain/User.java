package com.project.UserServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Lob;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="userService")
public class User
{
    @Id
    private String email;
    private String name,phoneNo;
    @Lob
    private byte[] profileImage;
    private List<Address> addresses;
    private List<Order> orders;
    private WishList wishLists;
}
