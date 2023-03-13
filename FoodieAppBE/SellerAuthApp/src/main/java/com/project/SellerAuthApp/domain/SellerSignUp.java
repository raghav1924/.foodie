package com.project.SellerAuthApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerSignUp
{
    private String email,password,role,phoneNo,name,confirmPassword;
}
