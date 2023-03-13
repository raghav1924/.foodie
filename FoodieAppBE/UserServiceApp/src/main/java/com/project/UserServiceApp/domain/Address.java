package com.project.UserServiceApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address
{
private String orderPlace,doorNo,street,city,state,zipcode;
}
