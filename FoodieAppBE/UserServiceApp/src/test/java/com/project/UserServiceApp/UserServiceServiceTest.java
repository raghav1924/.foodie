package com.project.UserServiceApp;


import com.project.UserServiceApp.domain.*;
import com.project.UserServiceApp.repository.IUserRepository;
import com.project.UserServiceApp.services.CUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceServiceTest {


    @Mock
    private IUserRepository iUserRepository;
    @InjectMocks
    private CUserService cUserService;

    private Address address;
    private CartItem cartItem;
    private FoodItems foodItems;
    private Order order;
    private User user;
    private WishList wishList;
    private List<CartItem> list1;
    private List<Address> list2;
    private List<Order> list3;
    private Set<String> cuisines;
    private Map<String,String> restaurant;

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
        cuisines=new HashSet<>();
        cuisines.add("abc");
        restaurant =new HashMap<>();
        restaurant.put("abc","abc");
        wishList = new WishList(cuisines,restaurant);
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
    public void getAllusersSuccess()
    {
        List<User> list=new ArrayList<>();
        list.add(user);
        when(iUserRepository.findAll()).thenReturn(list);
        assertEquals(list, cUserService.getAllUser());
    }
    @Test
    public void getAllusersFailure()
    {
        List<User> list=new ArrayList<>();
        list.add(user);
        when(iUserRepository.findAll()).thenReturn(list);
        assertNotEquals(list.get(0).getEmail(), cUserService.getAllUser());
    }

    @Test
    public void getUserWishlistAllCuisinesSuccess()
    {
        iUserRepository.save(user);
       when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
       assertEquals(cuisines,cUserService.getUserWishlistAllCuisines(user.getEmail()));
    }
    @Test
    public void getUserWishlistAllCuisinesFailure()
    {
        iUserRepository.save(user);
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertNotEquals(user,cUserService.getUserWishlistAllCuisines(user.getEmail()));
    }
    @Test
    public void getUserWishListAllRestaurantsSuccess()
    {
        iUserRepository.save(user);
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertEquals(restaurant,cUserService.getUserWishListAllRestaurants(user.getEmail()));
    }
    @Test
    public void getUserWishListAllRestaurantsFailure()
    {
        iUserRepository.save(user);
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertNotEquals(cuisines,cUserService.getUserWishListAllRestaurants(user.getEmail()));
    }
    @Test
    public void getUserAllAddressSuccess()
    {
        iUserRepository.save(user);
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertEquals(list2,cUserService.getUserAllAddress(user.getEmail()));
    }
    @Test
    public void getUserAllAddressFailure()
    {
        iUserRepository.save(user);
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertNotEquals(address,cUserService.getUserAllAddress(user.getEmail()));
    }

    @Test
    public void addUserAddressSuccess()
    {

        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(iUserRepository.save(user)).thenReturn(user);
        assertEquals(list2,cUserService.addUserAddress(user.getEmail(),address));
    }
    @Test
    public void addUserAddressFailure()
    {

        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(iUserRepository.save(user)).thenReturn(user);
        assertNotEquals(address,cUserService.addUserAddress(user.getEmail(),address));
    }
    @Test
    public void addCuisinesToUserWishlistSuccess()
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(iUserRepository.save(user)).thenReturn(user);
        assertEquals(cuisines,cUserService.addCuisinesToUserWishlist(user.getEmail(),"abc"));
    }
    @Test
    public void addCuisinesToUserWishlistFailure()
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(iUserRepository.save(user)).thenReturn(user);
        assertNotEquals(user,cUserService.addCuisinesToUserWishlist(user.getEmail(),"abc"));
    }
    @Test
    public  void addRestaurantToUserWishlistSuccess()
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(iUserRepository.save(user)).thenReturn(user);
        assertEquals(restaurant,cUserService.addRestaurantToUserWishlist(user.getEmail(),"abc","123"));
    }

    @Test
    public  void addRestaurantToUserWishlistFailure()
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(iUserRepository.save(user)).thenReturn(user);
        assertNotEquals(cuisines,cUserService.addRestaurantToUserWishlist(user.getEmail(),"abc","123"));
    }
}
