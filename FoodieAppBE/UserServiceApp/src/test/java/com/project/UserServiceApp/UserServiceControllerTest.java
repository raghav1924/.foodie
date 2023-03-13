package com.project.UserServiceApp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.UserServiceApp.contoller.CUserController;
import com.project.UserServiceApp.domain.*;
import com.project.UserServiceApp.services.CUserService;
import com.project.UserServiceApp.services.IOrderService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value= CUserController.class)
public class UserServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CUserService cUserService;

    @MockBean
    private IOrderService iOrderService;

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
    public void setup()
    {
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

// Token Generation

        Map<String,String> result = new HashMap<String,String>();
        Map<String,Object> userdata = new HashMap<>();
        userdata.put("user_email",user.getEmail());


        jwt = Jwts.builder()
                .setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "FoodieApp_key")
                .compact();
    }

    @AfterEach
    public void tear()
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
    public void getAllUserssSuccess() throws Exception {
        List<User> list4 = new ArrayList<>();
        list4.add(user);
        when(cUserService.getAllUser()).thenReturn(list4);
        mockMvc.perform(get("/userService/getAllUser")).andExpect(status().isOk())
                .andExpect( jsonPath("$[0].email").value("abc"))
                .andExpect( jsonPath("$[0].name").value("abc")).andExpect( jsonPath("$[0].phoneNo").value("abc"));

    }


    @Test
    public  void getUserDetailsSuccess() throws Exception {

        when(cUserService.getUserDetails(user.getEmail())).thenReturn(user);
        mockMvc.perform(get("/userService/getUserDetails") .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect( jsonPath("email").value("abc"))
                .andExpect( jsonPath("name").value("abc"))
                .andExpect( jsonPath("phoneNo").value("abc"));
    }


    @Test
    public  void getUserAllAddressSuccess() throws Exception {

        when(cUserService.getUserAllAddress(user.getEmail())).thenReturn(list2);
        mockMvc.perform(get("/userService/getUserAllAddress") .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect( jsonPath("$[0].orderPlace").value("abc"))
                .andExpect( jsonPath("$[0].doorNo").value("abc"))
                .andExpect( jsonPath("$[0].street").value("abc"));


    }

    @Test
    public  void addUserSuccess() throws Exception {

        when(cUserService.addUser(user)).thenReturn(user);
        mockMvc.perform(post("/userService/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public  void addOrderToUserSuccess() throws Exception {

        when(cUserService.addOrderToUser("abc",order)).thenReturn(order);
        mockMvc.perform(post("/userService/addUser").header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());
    }



}



