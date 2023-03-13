package com.project.UserAuthApp.services;


import com.project.UserAuthApp.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CUserToken implements IUserToken {

    @Override
    public Map<String, String> userGenerateToken(User user) {
        Map<String,String> result = new HashMap<String,String>();
        user.setPassword("");
        Map<String,Object> userdata = new HashMap<>();


        userdata.put("user_role",user.getRole());
        userdata.put("user_email",user.getEmail());


        String jwt = Jwts.builder()
                .setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "FoodieApp_key")
                .compact();

        result.put("token",jwt);
        result.put("message","User login success");
        result.put("role",user.getRole());
        return result;
    }
}
