package com.project.SellerServiceApp.contoller;

import com.project.SellerServiceApp.Exceptions.SellerNotFoundExceptions;
import com.project.SellerServiceApp.domain.Restaurant;
import com.project.SellerServiceApp.domain.Seller;
import com.project.SellerServiceApp.repository.ISellerRepository;
import com.project.SellerServiceApp.services.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/sellerService/")
public class CSellerController {

    @Autowired
    private ISellerService iSellerService;
    @Autowired
    private ISellerRepository iSellerRepository;


    @GetMapping("getSellerAllRestaurant")
    public ResponseEntity<?> getSellerAllRestaurant(HttpServletRequest request) throws SellerNotFoundExceptions {
        String email =(String) request.getAttribute("email");
        return new ResponseEntity<>(iSellerService.getSellerAllRestaurant(email),HttpStatus.OK);
    }
    @GetMapping("getSellerDetails")
    public ResponseEntity<?> getSellerDetails(HttpServletRequest request) throws SellerNotFoundExceptions {
        String email =(String) request.getAttribute("email");
        return new ResponseEntity<>(iSellerService.getSellerDetails(email),HttpStatus.OK);
    }
    @GetMapping("getAllSeller")
    public ResponseEntity<?> getAllSeller(){
        return new ResponseEntity<>(iSellerService.getAllSeller(),HttpStatus.OK);
    }
    @PostMapping("addSeller")
    public ResponseEntity<?> addSeller(@RequestBody Seller seller){
        seller.setRestaurants(new HashMap<>());
        return new ResponseEntity<>(iSellerService.addSeller(seller),HttpStatus.CREATED);
    }

    @DeleteMapping("deleteSeller")
    public ResponseEntity<?> deleteSeller(HttpServletRequest request) throws SellerNotFoundExceptions {
        String email =(String) request.getAttribute("email");
        iSellerService.deleteSeller(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("addRestaurantToSeller")
    public ResponseEntity<?> addRestaurantToSeller(@RequestBody Restaurant restaurant ,HttpServletRequest request){
        String email =(String) request.getAttribute("email");
        System.out.println(email);
        restaurant.setOrders(new ArrayList<>());
        restaurant.setMenu(new ArrayList<>());
        return new ResponseEntity<>(iSellerService.addRestaurantToSeller(email,restaurant),HttpStatus.OK);
    }

}
