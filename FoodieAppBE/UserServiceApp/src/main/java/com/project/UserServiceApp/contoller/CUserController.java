package com.project.UserServiceApp.contoller;

import com.project.UserServiceApp.domain.Address;
import com.project.UserServiceApp.domain.Order;
import com.project.UserServiceApp.domain.User;
import com.project.UserServiceApp.domain.WishList;
import com.project.UserServiceApp.exceptions.UserNotFoundExceptions;
import com.project.UserServiceApp.services.IOrderService;
import com.project.UserServiceApp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@RestController
@RequestMapping("/userService/")
public class CUserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderService iOrderService;

    @GetMapping("getAllUser")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(iUserService.getAllUser(), HttpStatus.OK);
    }
    @GetMapping("getUserDetails")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request)  {
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.getUserDetails(email), HttpStatus.OK);
    }
    @GetMapping("getUserOrderDetails")
    public ResponseEntity<?> getUserOrderDetails(HttpServletRequest request) throws UserNotFoundExceptions {
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.getUserOrderDetails(email), HttpStatus.OK);
    }
    @GetMapping("getUserWishlistAllCuisines")
    public ResponseEntity<?> getUserWishlistAllCuisines(HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.getUserWishlistAllCuisines(email), HttpStatus.OK);
    }
    @GetMapping("getUserWishListAllRestaurants")
    public ResponseEntity<?> getUserWishListAllRestaurants(HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.getUserWishListAllRestaurants(email), HttpStatus.OK);
    }
    @GetMapping("getUserAllAddress")
    public ResponseEntity<?> getUserAllAddress(HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.getUserAllAddress(email), HttpStatus.OK);
    }
    @PostMapping("addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user.setAddresses(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        user.setWishLists(new WishList());
        user.getWishLists().setCuisines(new HashSet<>());
        user.getWishLists().setRestaurants(new HashMap<>());
        return new ResponseEntity<>(iUserService.addUser(user), HttpStatus.OK);

    }
    @PostMapping("addUserAddress")
    public ResponseEntity<?> addUserAddress(@RequestBody Address address,HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.addUserAddress(email,address), HttpStatus.OK);

    }
    @PostMapping("addOrderToUser")
    public ResponseEntity<?> addOrderToUser(@RequestBody Order order, HttpServletRequest request){
        System.out.println("order ");
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.addOrderToUser(email,order), HttpStatus.OK);

    }
    @PostMapping("addCuisinesToUserWishlist")
    public ResponseEntity<?> addCuisinesToUserWishlist(@RequestBody String cuisineName, HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.addCuisinesToUserWishlist(email,cuisineName), HttpStatus.OK);

    }
    @PostMapping("addRestaurantToUserWishlist/{restaurantId}")
    public ResponseEntity<?> addRestaurantToUserWishlist(@RequestBody String restaurantName,@PathVariable String restaurantId, HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.addRestaurantToUserWishlist(email,restaurantName,restaurantId), HttpStatus.OK);

    }
    @PutMapping("updateUserProfileImage")
    public ResponseEntity<?> updateUserProfileImage(@RequestParam("profileImage") MultipartFile profileImage, HttpServletRequest request) throws IOException {
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.updateUserProfileImage(email,profileImage), HttpStatus.OK);

    }
    @PutMapping("updateUserAddress/{orderPlace}")
    public ResponseEntity<?> updateUserAddress(@PathVariable String orderPlace, @RequestBody Address address, HttpServletRequest request) throws IOException {
        String email=(String) request.getAttribute("email");
        System.out.println(email);
        return new ResponseEntity<>(iUserService.updateUserAddress(email,orderPlace,address), HttpStatus.OK);

    }
    @PutMapping("updateOrderStatusToUser/{orderId}/{OrderStatus}")
    public ResponseEntity<?> updateOrderStatusToUser(@PathVariable String orderId,@PathVariable String OrderStatus, @RequestBody Address address, HttpServletRequest request) throws IOException {
        String email=(String) request.getAttribute("email");
        return new ResponseEntity<>(iUserService.updateOrderStatusToUser(email,orderId,OrderStatus), HttpStatus.OK);

    }
    @DeleteMapping("deleteUser")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) throws UserNotFoundExceptions {
        String email=(String) request.getAttribute("email");
        iUserService.deleteUser(email);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    @DeleteMapping("deleteAddressFromUser/{orderPlace}")
    public ResponseEntity<?> deleteAddressFromUser(@PathVariable String orderPlace,HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        iUserService.deleteAddressFromUser(email,orderPlace);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    @DeleteMapping("deleteCuisineFromUserWishlist")
    public ResponseEntity<?> deleteCuisineFromUserWishlist(@RequestBody String cuisineName,HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        iUserService.deleteCuisineFromUserWishlist(email,cuisineName);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping("deleteRestaurantFromUserWishlist")
    public ResponseEntity<?> deleteRestaurantFromUserWishlist(@RequestBody String restaurantName,HttpServletRequest request){
        String email=(String) request.getAttribute("email");
        iUserService.deleteRestaurantFromUserWishlist(email,restaurantName);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
