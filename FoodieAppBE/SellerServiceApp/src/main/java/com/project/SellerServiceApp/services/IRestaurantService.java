package com.project.SellerServiceApp.services;

import com.project.SellerServiceApp.Exceptions.RestaurantNotFoundException;
import com.project.SellerServiceApp.domain.Cuisines;
import com.project.SellerServiceApp.domain.FoodItems;
import com.project.SellerServiceApp.domain.Order;
import com.project.SellerServiceApp.domain.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IRestaurantService {
    public Restaurant getRestaurantDetails(String restaurantId)throws RestaurantNotFoundException;
    public List<Restaurant> getRestaurantDetailsByName(String restaurantName);
    public List<Restaurant> getAllRestaurant();
    public List<Cuisines> getAllCuisines(String restaurantId);
    public List<FoodItems> getFoodItems(String restaurantId,String cuisineName);
    public List<Restaurant> getAllRestaurantsByCity(String city);
    public List<Restaurant> getAllRestaurantsByCuisine(String cuisineName,String city);
    public Restaurant updateRestaurantName(String restaurantId,String restaurantName)throws RestaurantNotFoundException;
    public Restaurant updateRestaurantImage(String restaurantId, MultipartFile restaurantImage) throws IOException,RestaurantNotFoundException;
    public Restaurant addCuisineToList (String restaurantId, Cuisines cuisines);
    public Restaurant addRestaurant (Restaurant restaurant);
    public Restaurant addFoodItem(String restaurantId,String cuisineName, FoodItems foodItems);
    public Restaurant updateFoodItem(String restaurantId,String cuisineName, FoodItems foodItems);
    public Restaurant updateFoodItemImage(String restaurantId,String cuisineName,String foodItemName, MultipartFile foodItemsImage) throws IOException;
    public void deleteCuisine(String restaurantId,String cuisineName)throws RestaurantNotFoundException;
    public void deleteFoodItem(String restaurantId, String cuisineName,String FoodItemName);
    public void deleteRestaurant (String restaurantId)throws RestaurantNotFoundException;

    // FOR ORDER UPDATE
    public Order updateOrderStatus(Order order);

    public Restaurant addOrderToRestaurant(Order order);

    public Set<String> getDistinctCityNames();


}
