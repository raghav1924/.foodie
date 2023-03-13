package com.project.SellerServiceApp;


import com.project.SellerServiceApp.Exceptions.SellerNotFoundExceptions;
import com.project.SellerServiceApp.domain.*;
import com.project.SellerServiceApp.repository.IRestaurantRepository;
import com.project.SellerServiceApp.repository.ISellerRepository;
import com.project.SellerServiceApp.services.CRestaurantService;
import com.project.SellerServiceApp.services.CSellerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceServiceTest {

    @Mock
    private ISellerRepository iSellerRepository;
    @InjectMocks
    private CSellerService cSellerService;

    @Mock
    private IRestaurantRepository iRestaurantRepository;

    @InjectMocks
    private CRestaurantService cRestaurantService;

    private Seller seller;
    private List<Cuisines> menu;
    private Address address;
    private List<Order> orders;
    private CartItem cartItem;
    private Order order;
    private Cuisines cuisines;
    private FoodItems foodItems;
    private Restaurant restaurant;
    private Map<String,String> restaurants;

    @BeforeEach
    public void setUp(){
        menu=new ArrayList<>();
        orders=new ArrayList<>();
        restaurants=new HashMap<>();
        restaurants.put("abc","abc");
        menu.add(cuisines);
        orders.add(order);
        restaurant=new Restaurant("abc","abc",null,menu,address,"abc",orders);
        address=new Address("abc","abc","abc","123");
        seller  = new Seller("abc","abc","abc",restaurants);
    }

    @AfterEach
    public void tearDown()
    {
        seller = null;
        restaurants=null;
        menu=null;
        orders=null;
        address=null;
    }

    @Test
    public void getSellerAllRestaurantSuccess() throws SellerNotFoundExceptions {
        iSellerRepository.save(seller);
        when(iSellerRepository.findById(seller.getEmail())).thenReturn(Optional.ofNullable(seller));
        assertEquals(restaurant.getRestaurantId(), cSellerService.getSellerAllRestaurant(seller.getEmail()).get("abc"));
    }
    @Test
    public void getSellerAllRestaurantFailure() throws SellerNotFoundExceptions {
        iSellerRepository.save(seller);
        when(iSellerRepository.findById(seller.getEmail())).thenReturn(Optional.ofNullable(seller));
        assertNotEquals(restaurant, cSellerService.getSellerAllRestaurant(seller.getEmail()).get("abc"));
    }
    @Test
    public void getSellerDetailsSuccess() throws SellerNotFoundExceptions {
        iSellerRepository.save(seller);
        when(iSellerRepository.findById(seller.getEmail())).thenReturn(Optional.ofNullable(seller));
        assertEquals(seller,cSellerService.getSellerDetails(seller.getEmail()));
    }
    @Test
    public void getSellerDetailsFailure() throws SellerNotFoundExceptions {
        iSellerRepository.save(seller);
        when(iSellerRepository.findById(seller.getEmail())).thenReturn(Optional.ofNullable(seller));
        assertNotEquals(seller.getEmail(),cSellerService.getSellerDetails(seller.getEmail()));
    }

}
