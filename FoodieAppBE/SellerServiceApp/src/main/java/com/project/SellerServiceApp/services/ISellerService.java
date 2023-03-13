package com.project.SellerServiceApp.services;

import com.project.SellerServiceApp.Exceptions.SellerNotFoundExceptions;
import com.project.SellerServiceApp.domain.Restaurant;
import com.project.SellerServiceApp.domain.Seller;

import java.util.List;
import java.util.Map;

public interface ISellerService {
    public Map<String,String> getSellerAllRestaurant(String email)throws SellerNotFoundExceptions;
    public Seller getSellerDetails(String email)throws SellerNotFoundExceptions;
    public List<Seller> getAllSeller();
    public Seller addSeller(Seller seller);
    public void deleteSeller(String email)throws SellerNotFoundExceptions;
    public Map<String, String> addRestaurantToSeller(String email,Restaurant restaurant);

}
