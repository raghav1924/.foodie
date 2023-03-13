package com.project.SellerServiceApp.services;

import com.project.SellerServiceApp.Exceptions.SellerNotFoundExceptions;
import com.project.SellerServiceApp.domain.Restaurant;
import com.project.SellerServiceApp.domain.Seller;
import com.project.SellerServiceApp.repository.IRestaurantRepository;
import com.project.SellerServiceApp.repository.ISellerRepository;
import com.project.SellerServiceApp.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CSellerService implements ISellerService{
    @Autowired
    private ISellerRepository iSellerRepository;
    @Autowired
    private IRestaurantRepository iRestaurantRepository;

    @Override
    public Map<String, String> getSellerAllRestaurant(String email)throws SellerNotFoundExceptions {
        if(iSellerRepository.findById(email).isPresent())
        {
            return iSellerRepository.findById(email).get().getRestaurants();
        }
       else {
           throw new SellerNotFoundExceptions();
        }
    }

    @Override
    public Seller getSellerDetails(String email)throws SellerNotFoundExceptions {
        if(iSellerRepository.findById(email).isPresent())
        {
            return iSellerRepository.findById(email).get();
        }
        else {
            throw new SellerNotFoundExceptions();
        }
    }

    @Override
    public List<Seller> getAllSeller() {
        return iSellerRepository.findAll();
    }

    @Override
    public Seller addSeller(Seller seller) {
        return iSellerRepository.insert(seller);
    }

    @Override
    public void deleteSeller(String email)throws SellerNotFoundExceptions {
        if(iSellerRepository.findById(email).isPresent())
        {
            for (Map.Entry<String,String> restaurant:getSellerAllRestaurant(email).entrySet()) {
                iRestaurantRepository.deleteById(restaurant.getKey());
            }
            iSellerRepository.deleteById(email);
        }
        else {
            throw new SellerNotFoundExceptions();
        }
    }

    @Override
    public Map<String, String> addRestaurantToSeller(String email,Restaurant restaurant) {
        Seller seller=iSellerRepository.findById(email).get();
        restaurant.setSellerEmail(email);
        restaurant.setRestaurantImage(iRestaurantRepository.findById("123").get().getRestaurantImage());
        Restaurant restaurant1= iRestaurantRepository.insert(restaurant);
        seller.getRestaurants().put(restaurant1.getRestaurantId(), restaurant1.getRestaurantName());
        return iSellerRepository.save(seller).getRestaurants();
    }
}
