package com.project.SellerServiceApp.contoller;

import com.project.SellerServiceApp.Exceptions.RestaurantNotFoundException;
import com.project.SellerServiceApp.domain.Cuisines;
import com.project.SellerServiceApp.domain.FoodItems;
import com.project.SellerServiceApp.domain.Order;
import com.project.SellerServiceApp.domain.Restaurant;
import com.project.SellerServiceApp.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/restaurantService/")
public class CRestaurantController {

    @Autowired
    private IRestaurantService iRestaurantService;

    @GetMapping("getRestaurantDetails/{restaurantId}")
    public ResponseEntity<?> getRestaurantDetails(@PathVariable String restaurantId) throws RestaurantNotFoundException {
        return new ResponseEntity<>(iRestaurantService.getRestaurantDetails(restaurantId), HttpStatus.OK);
    }
    @GetMapping("getRestaurantDetailsByName/{restaurantName}")
    public ResponseEntity<?> getRestaurantDetailsByName(@PathVariable String restaurantName){
        return new ResponseEntity<>(iRestaurantService.getRestaurantDetailsByName(restaurantName), HttpStatus.OK);
    }
    @GetMapping("getAllRestaurant")
    public ResponseEntity<?> getAllRestaurant(){
        return new ResponseEntity<>(iRestaurantService.getAllRestaurant(), HttpStatus.OK);
    }
    @GetMapping("getAllCuisines/{restaurantId}")
    public ResponseEntity<?> getAllCuisines(@PathVariable String restaurantId){
        return new ResponseEntity<>(iRestaurantService.getAllCuisines(restaurantId), HttpStatus.OK);
    }
    @GetMapping("getFoodItems/{restaurantId}/{cuisineName}")
    public ResponseEntity<?> getFoodItems(@PathVariable String restaurantId,@PathVariable String cuisineName){
        return new ResponseEntity<>(iRestaurantService.getFoodItems(restaurantId,cuisineName), HttpStatus.OK);
    }
    @GetMapping("getAllRestaurantsByCity/{city}")
    public ResponseEntity<?> getAllRestaurantsByCity(@PathVariable String city){

        return new ResponseEntity<>(iRestaurantService.getAllRestaurantsByCity(city), HttpStatus.OK);
    }
    @GetMapping("getAllRestaurantsByCuisine/{cuisineName}/{city}")
    public ResponseEntity<?> getAllRestaurantsByCuisine(@PathVariable String cuisineName,@PathVariable String city){

        return new ResponseEntity<>(iRestaurantService.getAllRestaurantsByCuisine(cuisineName,city), HttpStatus.OK);
    }
    @PutMapping("updateRestaurantName")
    public ResponseEntity<?> updateRestaurantName(@RequestBody Restaurant restaurant) throws RestaurantNotFoundException {
        return new ResponseEntity<>(iRestaurantService.updateRestaurantName(restaurant.getRestaurantId(),restaurant.getRestaurantName()), HttpStatus.OK);
    }
    @PutMapping("updateRestaurantImage/{restaurantId}")
    public ResponseEntity<?> updateRestaurantImage(@PathVariable String restaurantId, @RequestParam("restaurantImage") MultipartFile restaurantImage) throws IOException, RestaurantNotFoundException {
        return new ResponseEntity<>(iRestaurantService.updateRestaurantImage(restaurantId, restaurantImage), HttpStatus.OK);
    }
    @PutMapping("updateFoodItem/{restaurantId}/{cuisineName}")
    public ResponseEntity<?> updateFoodItem(@PathVariable String restaurantId, @PathVariable String cuisineName, @RequestBody FoodItems foodItems) throws RestaurantNotFoundException {
        return new ResponseEntity<>(iRestaurantService.updateFoodItem(restaurantId, cuisineName,foodItems), HttpStatus.OK);
    }
    @PutMapping("updateFoodItemImage/{restaurantId}/{cuisineName}/{foodItemName}")
    public ResponseEntity<?> updateFoodItemImage(@PathVariable String restaurantId, @PathVariable String cuisineName,@PathVariable String foodItemName, @RequestParam("foodItemImage") MultipartFile foodItemImage) throws RestaurantNotFoundException, IOException {
        return new ResponseEntity<>(iRestaurantService.updateFoodItemImage(restaurantId, cuisineName,foodItemName,foodItemImage), HttpStatus.OK);
    }

    @PostMapping("addCuisineToList/{restaurantId}")
    public ResponseEntity<?> addCuisineToList(@PathVariable String restaurantId, @RequestBody Cuisines cuisines) {
        cuisines.setFoodItems(new ArrayList<>());
        return new ResponseEntity<>(iRestaurantService.addCuisineToList(restaurantId,cuisines), HttpStatus.OK);

    }
    @PostMapping("addRestaurant")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant){
        restaurant.setMenu(new ArrayList<>());
        restaurant.setOrders(new ArrayList<>());
        return new ResponseEntity<>(iRestaurantService.addRestaurant(restaurant), HttpStatus.OK);
    }
    @PostMapping("addFoodItem/{restaurantId}/{cuisineName}")
    public ResponseEntity<?> addFoodItem(@PathVariable String restaurantId, @PathVariable String cuisineName, @RequestBody FoodItems foodItems) {
        return new ResponseEntity<>(iRestaurantService.addFoodItem(restaurantId,cuisineName,foodItems), HttpStatus.OK);
    }

    @DeleteMapping("deleteCuisine/{restaurantId}/{cuisineName}")
    public ResponseEntity<?> deleteCuisine(@PathVariable String restaurantId,@PathVariable String cuisineName) throws RestaurantNotFoundException {
        iRestaurantService.deleteCuisine(restaurantId,cuisineName);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    @DeleteMapping("deleteFoodItem/{restaurantId}/{cuisineName}/{foodItemName}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable String restaurantId,@PathVariable  String cuisineName,@PathVariable String foodItemName){
        iRestaurantService.deleteFoodItem(restaurantId,cuisineName,foodItemName);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping("deleteRestaurant/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant (@PathVariable String restaurantId) throws RestaurantNotFoundException {
        iRestaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    //order status update
    @PutMapping("updateOrderStatus")
    public ResponseEntity<?> updateOrderStatus(@RequestBody Order order) {
        return new ResponseEntity<>(iRestaurantService.updateOrderStatus(order), HttpStatus.OK);
    }

    @GetMapping("/RestaurantByCity")
    public ResponseEntity<?> getDistinctCityNames()
    {
        return new ResponseEntity<>(iRestaurantService.getDistinctCityNames(),HttpStatus.OK);
    }
}
