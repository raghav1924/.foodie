package com.project.SellerAuthApp.controller;

import com.project.SellerAuthApp.Exceptions.ContactAlreadyRegistered;
import com.project.SellerAuthApp.Exceptions.EmailAlreadyRegistered;
import com.project.SellerAuthApp.Exceptions.InvalidCredentials;
import com.project.SellerAuthApp.domain.Seller;
import com.project.SellerAuthApp.domain.SellerSignUp;
import com.project.SellerAuthApp.services.SellerService;
import com.project.SellerAuthApp.services.Token.SellerToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellerAuth/")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerToken sellerToken;

    //http://localhost:9999/api/seller/otp
    @PostMapping("/otp")
    public ResponseEntity<?> getOtp(@RequestBody String email)
    {
        System.out.println(email);
        return new ResponseEntity<>(sellerService.sendOTP(email), HttpStatus.OK);
    }
    @PostMapping("/sellerRegistration")
    public ResponseEntity<?> userRegistration(@RequestBody SellerSignUp sellerSignUp) throws EmailAlreadyRegistered, ContactAlreadyRegistered {
        return new ResponseEntity<>(sellerService.sellerRegistration(sellerSignUp), HttpStatus.CREATED);
    }
    @PostMapping("/sellerLogIn")
    public ResponseEntity<?> userLogIn(@RequestBody Seller seller) throws InvalidCredentials {
        Seller checkedSellerLogIn=sellerService.sellerLogIn(seller);
        if(checkedSellerLogIn!=null)
        {
            return new ResponseEntity<>(sellerToken.sellerGenerateToken(checkedSellerLogIn),HttpStatus.OK);
        }
        else {
            throw new  InvalidCredentials();
        }
    }

}
