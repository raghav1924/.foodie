package com.project.SellerServiceApp;

import com.project.SellerServiceApp.domain.*;
import com.project.SellerServiceApp.repository.IRestaurantRepository;
import com.project.SellerServiceApp.repository.ISellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class SellerServiceRepositoryTest {

    @Autowired
    private ISellerRepository iSellerRepository;

    @Autowired
    private IRestaurantRepository iRestaurantRepository;
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
    public void getSellerAllRestaurantSuccess()
    {
        iSellerRepository.save(seller);
        assertEquals(restaurants,iSellerRepository.findById(seller.getEmail()).get().getRestaurants());
    }
    @Test
    public void getSellerAllRestaurantFailure()
    {
        iSellerRepository.save(seller);
        assertNotEquals(address,iSellerRepository.findById(seller.getEmail()).get().getRestaurants());
    }
    @Test
    public void getSellerDetailsSuccess()
    {
        iSellerRepository.save(seller);
        assertEquals(seller,iSellerRepository.findById(seller.getEmail()).get());
    }
    @Test
    public void getSellerDetailsFailure()
    {
        iSellerRepository.save(seller);
        assertNotEquals(address,iSellerRepository.findById(seller.getEmail()).get());
    }
    @Test
    public void getAllSellerSuccess()
    {
        List<Seller> list = iSellerRepository.findAll();
        assertEquals(3, list.size());
    }
    @Test
    public void getAllSellerFailure()
    {
        List<Seller> list = iSellerRepository.findAll();
        assertNotEquals(2, list.size());
    }
    @Test
    public void addSellerrSuccess()
    {
        Seller seller1=iSellerRepository.save(seller);
        assertEquals(seller,seller1);
    }
    @Test
    public void addSellerrFailure()
    {
        Seller seller1=iSellerRepository.save(seller);
        assertNotEquals(seller,seller1.getEmail());
    }
    @Test
    public void addRestaurantToSellerSuccess()
    {
        iSellerRepository.save(seller);
        assertEquals(restaurants,iSellerRepository.findById(seller.getEmail()).get().getRestaurants());
    }
    @Test
    public void addRestaurantToSellerFailure()
    {
        iSellerRepository.save(seller);
        assertNotEquals(seller,iSellerRepository.findById(seller.getEmail()).get().getRestaurants());
    }
    @Test
    public void getRestaurantDetailsSucccess()
    {
      iRestaurantRepository.save(restaurant);
      assertEquals(restaurant,iRestaurantRepository.findById(restaurant.getRestaurantId()).get());
    }
    @Test
    public void getRestaurantDetailsFailure()
    {
        iRestaurantRepository.save(restaurant);
        assertNotEquals(seller,iRestaurantRepository.findById(restaurant.getRestaurantId()).get());
    }

}
