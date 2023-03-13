package com.project.SellerServiceApp.services;

import com.project.SellerServiceApp.Exceptions.RestaurantNotFoundException;
import com.project.SellerServiceApp.domain.Cuisines;
import com.project.SellerServiceApp.domain.FoodItems;
import com.project.SellerServiceApp.domain.Order;
import com.project.SellerServiceApp.domain.Restaurant;
import com.project.SellerServiceApp.rabbitmq.Producer;
import com.project.SellerServiceApp.repository.IRestaurantRepository;
import com.project.SellerServiceApp.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CRestaurantService implements IRestaurantService{
    @Autowired
    private IRestaurantRepository iRestaurantRepository;
    @Autowired
    private Producer producer;

    @Override
    public Restaurant getRestaurantDetails(String restaurantId)throws RestaurantNotFoundException
    {
        if(iRestaurantRepository.findById(restaurantId).isPresent())
        {
            Restaurant restaurant1= iRestaurantRepository.findById(restaurantId).get();
            restaurant1.setRestaurantImage(ImageUtils.decompressImage(restaurant1.getRestaurantImage()));
            return restaurant1;
        }
        throw new RestaurantNotFoundException();
    }

    @Override
    public List<Restaurant> getRestaurantDetailsByName(String restaurantName)
    {
        return iRestaurantRepository.findByRestaurantName(restaurantName);
    }

    @Override
    public List<Restaurant> getAllRestaurant()
    {
        List<Restaurant> restaurants= iRestaurantRepository.findAll();
        for(Restaurant restaurant1:restaurants)
        {
            if(restaurant1.getRestaurantId().equalsIgnoreCase("123"))
            {
                restaurants.remove(restaurant1);break;
            }
        }
        for(Restaurant restaurant:restaurants)
        {
            restaurant.setRestaurantImage(ImageUtils.decompressImage(restaurant.getRestaurantImage()));
        }

        return restaurants;
    }

    @Override
    public List<Cuisines> getAllCuisines(String restaurantId)
    {
            Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
            return restaurant.getMenu();
    }

    @Override
    public List<FoodItems> getFoodItems(String restaurantId, String cuisineName)
    {
        Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
        for (Cuisines cuisine:restaurant.getMenu())
        {
            if (cuisine.getCuisineName().equalsIgnoreCase(cuisineName))
            {
                return cuisine.getFoodItems();
            }
        }
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurantsByCity(String city)
    {
        List<Restaurant> listOfRestaurantsByCity=new ArrayList<>();
        for (Restaurant restaurant:iRestaurantRepository.findAll())
        {
            if(restaurant.getRestaurantId().equals("123"))continue;
            else {
            if (restaurant.getAddress().getCity().equalsIgnoreCase(city))
            {
                restaurant.setRestaurantImage(ImageUtils.decompressImage(restaurant.getRestaurantImage()));
                listOfRestaurantsByCity.add(restaurant);
            }}
        }
        return listOfRestaurantsByCity;
    }

    @Override
    public List<Restaurant> getAllRestaurantsByCuisine(String cuisineName, String city)
    {
        List<Restaurant> listOfRestaurantsByCity=getAllRestaurantsByCity(city);
        List<Restaurant> listOfRestaurantsByCuisine=new ArrayList<>();
        for (Restaurant restaurant:listOfRestaurantsByCity)
        {
//                    System.out.println(restaurant.getRestaurantImage());
            for (Cuisines cuisines:restaurant.getMenu())
            {
                if (cuisines.getCuisineName().equalsIgnoreCase(cuisineName))
                {
//                    System.out.println(restaurant.getRestaurantImage());
//                    restaurant.setRestaurantImage(ImageUtils.decompressImage(restaurant.getRestaurantImage()));
//                    System.out.println(restaurant.getRestaurantImage());
                    listOfRestaurantsByCuisine.add(restaurant);
                }
            }

        }
        return listOfRestaurantsByCuisine;
    }

    @Override
    public Restaurant updateRestaurantName(String restaurantId, String restaurantName)throws RestaurantNotFoundException
    {
        if(iRestaurantRepository.findById(restaurantId).isPresent())
        {
            Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
            restaurant.setRestaurantName(restaurantName);
            return iRestaurantRepository.save(restaurant);
        }
        throw new RestaurantNotFoundException();
    }

    @Override
    public Restaurant updateRestaurantImage(String restaurantId, MultipartFile restaurantImage) throws IOException,RestaurantNotFoundException
    {
         if(iRestaurantRepository.findById(restaurantId).isPresent())
         {
             Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
             restaurant.setRestaurantImage(ImageUtils.compressImage(restaurantImage.getBytes()));
             Restaurant restaurant1=iRestaurantRepository.save(restaurant);
             restaurant1.setRestaurantImage(ImageUtils.decompressImage(restaurant1.getRestaurantImage()));
             return restaurant1;
         }
       throw new RestaurantNotFoundException();
    }



    @Override
    public Restaurant addCuisineToList(String restaurantId, Cuisines cuisines)
    {
        Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
        restaurant.getMenu().add(cuisines);
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant)
    {
        return iRestaurantRepository.insert(restaurant);
    }

    @Override
    public Restaurant addFoodItem(String restaurantId,String cuisineName, FoodItems foodItems)
    {
        foodItems.setFoodImage(ImageUtils.decompressImage(iRestaurantRepository.findById("123").get().getRestaurantImage()));
        Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
        for (Cuisines cuisines:restaurant.getMenu())
        {
            if (cuisines.getCuisineName().equalsIgnoreCase(cuisineName))
            {
                cuisines.getFoodItems().add(foodItems);
            }
        }
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateFoodItem(String restaurantId,String cuisineName, FoodItems foodItems)
    {
        Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
        for (Cuisines cuisines:restaurant.getMenu())
        {
            if (cuisines.getCuisineName().equalsIgnoreCase(cuisineName))
            {
                System.out.println(cuisines);
                for (FoodItems foodItem:cuisines.getFoodItems())
                {
                    System.out.println(foodItem);
                    if (foodItem.getItemName().equalsIgnoreCase(foodItems.getItemName()))
                    {
                        foodItem.setItemPrice(foodItems.getItemPrice());
                        foodItem.setItemDescription(foodItems.getItemDescription());
//                          foodItem.setFoodImage(ImageUtils.compressImage(foodItems.getFoodImage()));
//                            cuisines.getFoodItems().add(foodItem);
                        return iRestaurantRepository.save(restaurant);

                    }
                }
            }
        }
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateFoodItemImage(String restaurantId, String cuisineName,String foodItemName, MultipartFile foodItemsImage) throws IOException {
        Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
        for (Cuisines cuisines:restaurant.getMenu())
        {
            if (cuisines.getCuisineName().equalsIgnoreCase(cuisineName))
            {
                System.out.println(cuisines);
                for (FoodItems foodItem:cuisines.getFoodItems())
                {
                    System.out.println(foodItem);
                    System.out.println(foodItemName);
                    if (foodItem.getItemName().equalsIgnoreCase(foodItemName))
                    {
                          foodItem.setFoodImage(foodItemsImage.getBytes());
                        return iRestaurantRepository.save(restaurant);

                    }
                }
            }
        }
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public void deleteCuisine(String restaurantId, String cuisineName)throws RestaurantNotFoundException
    {
        Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
        for (Cuisines cuisines:restaurant.getMenu()) {
            if (cuisines.getCuisineName().equalsIgnoreCase(cuisineName))
            {
                restaurant.getMenu().remove(cuisines);
                iRestaurantRepository.save(restaurant);
                return;
            }
        }
        return;
    }

    @Override
    public void deleteFoodItem(String restaurantId, String cuisineName, String foodItemName)
    {
        Restaurant restaurant=iRestaurantRepository.findById(restaurantId).get();
        for (Cuisines cuisine:restaurant.getMenu())
        {
            if (cuisine.getCuisineName().equalsIgnoreCase(cuisineName))
                for (FoodItems foodItem:cuisine.getFoodItems())
                {
                    System.out.println(foodItem);
                    if (foodItem.getItemName().equalsIgnoreCase(foodItemName)) {
                        cuisine.getFoodItems().remove(foodItem);
                        iRestaurantRepository.save(restaurant);
                        return;
                    }
                }
        }
    }

    @Override
    public void deleteRestaurant(String restaurantId)throws RestaurantNotFoundException
    {
        if(iRestaurantRepository.findById(restaurantId).isPresent())
        {
            iRestaurantRepository.deleteById(restaurantId);
        }
        else {
            throw new RestaurantNotFoundException();
        }
    }

//    Order Status update
    @Override
    public Order updateOrderStatus(Order order) {


        Restaurant restaurant=iRestaurantRepository.findById(order.getRestaurantId()).get();
        for (Order order1:restaurant.getOrders())
        {
            if (order1.getOrderId().equalsIgnoreCase(order.getOrderId()))
            {
                System.out.println("inside update Order Status");
                order1.setOrderStatus(order.getOrderStatus());
                iRestaurantRepository.save(restaurant);
                producer.orderStatusToQueue(order1);
                return order1;
            }
        }
        return null;
    }

    @Override
    public Restaurant addOrderToRestaurant(Order order)
    {
        Restaurant restaurant=iRestaurantRepository.findById(order.getRestaurantId()).get();
        restaurant.getOrders().add(order);
        return iRestaurantRepository.save(restaurant);
    }


    @Override
    public Set<String> getDistinctCityNames() {
        return  iRestaurantRepository.findDistinctCityNames();
    }
}
