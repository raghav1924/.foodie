package com.project.SellerServiceApp;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.project.SellerServiceApp.contoller.CSellerController;
import com.project.SellerServiceApp.domain.*;
import com.project.SellerServiceApp.services.CSellerService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@WebMvcTest(value= CSellerController.class)
public class SellerServiceControllerTest {
    @Mock
    private CSellerService cSellerService;

    @InjectMocks
    private CSellerController cSellerController;
    @Autowired
    private MockMvc mockMvc;

    private Seller seller;

    private List<Cuisines> menu;
    private Address address;
    private List<Order> orders;
    private CartItem cartItem;
    private Order order;
    private Cuisines cuisines;
    private FoodItems foodItems;

    private Map<String,String> restaurants;

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp(){
        menu=new ArrayList<>();
        orders=new ArrayList<>();
        restaurants=new HashMap<>();
        restaurants.put("abc","abc");
        menu.add(cuisines);
        orders.add(order);
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

}
