package com.project.UserServiceApp;

import com.project.UserServiceApp.domain.*;
import com.project.UserServiceApp.repository.IUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserServiceRepositoryTest {

    @Autowired
    private IUserRepository iUserRepository;
    private Address address;
    private CartItem cartItem;
    private FoodItems foodItems;
    private Order order;
    private User user;
    private WishList wishList;
    private List<CartItem> list1;
    private List<Address> list2;
    private List<Order> list3;

    private String jwt;
    @BeforeEach
    public void setUp(){
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        address = new Address("abc","abc","abc","abc","abc","abc");
        foodItems = new FoodItems("abc","450","abc",null);
        cartItem = new CartItem(foodItems,5,600);
        list1.add(cartItem);
        order = new Order("abc","abc","abc","abc","abc","abc",list1,500,address);
        list2.add(address);
        list3.add(order);
        wishList = new WishList();
        user = new User("abc","abc","abc",null,list2,list3,wishList);

    }
    @AfterEach
    public void tearDown()
    {
        address = null;
        foodItems=null;
        cartItem = null;
        order = null ;
        wishList = null;
        list1= Collections.emptyList();
        list2 = Collections.emptyList();
        list3 = Collections.emptyList();
    }

    @Test
    public void getAllUsersSuccess()
    {
        List<User> list = iUserRepository.findAll();
        assertEquals(3, list.size());
    }
    @Test
    public void getAllUsersFailure()
    {
        List<User> list = iUserRepository.findAll();
        assertNotEquals(5, list.size());
    }

    @Test
    public void getUserDetailsSuccess()
    {
     iUserRepository.save(user);
        User user1 = iUserRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertEquals(user, user1);
    }
    @Test
    public void getUserDetailsFailure()
    {
        iUserRepository.save(user);
        User user1 = iUserRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertNotEquals(user, user1.getEmail());
    }
    @Test
    public void getUserOrderDetailsSuccess()
    {
        iUserRepository.findById(user.getEmail()).get();
         List<Order> list =iUserRepository.findById(user.getEmail()).get().getOrders();
         assertEquals(list.get(0),order);
    }

    @Test
    public void getUserOrderDetailsFailure()
    {
        iUserRepository.findById(user.getEmail()).get();
        List<Order> list =iUserRepository.findById(user.getEmail()).get().getOrders();
        assertNotEquals(list,order);
    }

    @Test
    public void addUserSuccess()
    {
        User user1=iUserRepository.save(user);
        assertEquals(user,user1);
    }
    @Test
    public void addUserFailure()
    {
        User user1=iUserRepository.save(user);
        assertNotEquals(user,user1.getEmail());
    }

    @Test
    public void addAddressSucccess()
    {
        iUserRepository.save(user);
        User user1=iUserRepository.findById(user.getEmail()).get();
        List<Address> addresses=user1.getAddresses();
        addresses.add(address);
        user1.setAddresses(addresses);
        iUserRepository.save(user1);
        assertEquals(user1.getAddresses(),addresses);
    }
    @Test
    public void addAddressFailure()
    {
        iUserRepository.save(user);
        User user1=iUserRepository.findById(user.getEmail()).get();
        List<Address> addresses=user1.getAddresses();
        addresses.add(address);
        user1.setAddresses(addresses);
        iUserRepository.save(user1);
        assertNotEquals(user.getAddresses(),user1.getAddresses());
    }
    @Test
    public void getUserAllAddressSuccess()
    {
        iUserRepository.findById(user.getEmail()).get();
       List<Address> addresses=iUserRepository.findById(user.getEmail()).get().getAddresses();
        assertEquals(2, addresses.size());
    }
    @Test
    public void getUserAllAddressFailure()
    {
        iUserRepository.findById(user.getEmail()).get();
        List<Address> addresses=iUserRepository.findById(user.getEmail()).get().getAddresses();
        assertNotEquals(2, addresses.size());
    }
}
