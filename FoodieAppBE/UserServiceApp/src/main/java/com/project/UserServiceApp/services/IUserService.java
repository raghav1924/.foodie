package com.project.UserServiceApp.services;

import com.project.UserServiceApp.domain.Address;
import com.project.UserServiceApp.domain.Order;
import com.project.UserServiceApp.domain.User;
import com.project.UserServiceApp.exceptions.UserNotFoundExceptions;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUserService {
    public List<User> getAllUser();
    public User getUserDetails(String email);
    public List<Order> getUserOrderDetails(String email)throws UserNotFoundExceptions;
    public Set<String> getUserWishlistAllCuisines(String email);
    public Map<String,String> getUserWishListAllRestaurants(String email);
    public List<Address> getUserAllAddress(String email);

    public User addUser(User user);
    public List<Address> addUserAddress(String email,Address address);
    public Order addOrderToUser(String email,Order order);
    public Set<String> addCuisinesToUserWishlist(String email,String cuisine);
    public Map<String,String> addRestaurantToUserWishlist(String email,String restaurantName,String restaurantId);
    public User updateUserProfileImage(String email, MultipartFile profileImage)throws IOException;
    public List<Address> updateUserAddress(String email,String orderPlace,Address address);
    public Order updateOrderStatusToUser(String email,String orderId,String OrderStatus);
    public void deleteUser(String email)throws UserNotFoundExceptions;
    public List<Address> deleteAddressFromUser(String email,String orderPlace);
    public Set<String> deleteCuisineFromUserWishlist(String email,String cuisineName);
    public Map<String,String> deleteRestaurantFromUserWishlist(String email,String restaurantName);




}
