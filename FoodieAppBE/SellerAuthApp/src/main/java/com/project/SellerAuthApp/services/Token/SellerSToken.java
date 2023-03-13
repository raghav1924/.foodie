package com.project.SellerAuthApp.services.Token;

import com.project.SellerAuthApp.domain.Seller;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SellerSToken implements SellerToken{
    @Override
    public Map<String, String> sellerGenerateToken(Seller seller) {
        Map<String,String> result = new HashMap<String,String>();
        seller.setPassword("");
        Map<String,Object> sellerdata = new HashMap<>();
        sellerdata.put("seller_role",seller.getRole());
        sellerdata.put("seller_email",seller.getEmail());
        String jwt = Jwts.builder()
                .setClaims(sellerdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "FoodieApp_key")
                .compact();
        result.put("token",jwt);
        result.put("message","seller login success");
        result.put("role",seller.getRole());
        result.put("seller_email",seller.getEmail());
        return result;
    }
}
