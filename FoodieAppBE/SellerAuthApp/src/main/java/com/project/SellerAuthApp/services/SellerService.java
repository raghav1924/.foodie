package com.project.SellerAuthApp.services;

import com.project.SellerAuthApp.Exceptions.ContactAlreadyRegistered;
import com.project.SellerAuthApp.Exceptions.EmailAlreadyRegistered;
import com.project.SellerAuthApp.Exceptions.InvalidCredentials;
import com.project.SellerAuthApp.Exceptions.SellerNotFoundException;
import com.project.SellerAuthApp.domain.Seller;
import com.project.SellerAuthApp.domain.SellerSignUp;

public interface SellerService {
    public int generateOTP();
    public int sendOTP(String email);
    public Seller sellerRegistration(SellerSignUp sellerSignUp)throws EmailAlreadyRegistered, ContactAlreadyRegistered;
    public Seller sellerLogIn(Seller seller)throws InvalidCredentials;
    public void deleteSeller(String email)throws SellerNotFoundException;
}
