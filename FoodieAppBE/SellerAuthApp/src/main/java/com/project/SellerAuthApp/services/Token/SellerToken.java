package com.project.SellerAuthApp.services.Token;

import com.project.SellerAuthApp.domain.Seller;

import java.util.Map;

public interface SellerToken {
    public Map<String,String> sellerGenerateToken(Seller seller);
}
