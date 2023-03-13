package com.project.UserServiceApp.services;

import com.project.UserServiceApp.domain.Address;
import com.project.UserServiceApp.domain.Order;
import com.project.UserServiceApp.domain.User;
import com.project.UserServiceApp.exceptions.UserNotFoundExceptions;
import com.project.UserServiceApp.rabbitmq.EmailDTO;
import com.project.UserServiceApp.rabbitmq.Producer;
import com.project.UserServiceApp.repository.IOrderRepository;
import com.project.UserServiceApp.repository.IUserRepository;
import com.project.UserServiceApp.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CUserService implements IUserService{
    @Autowired
    private IUserRepository iUserRepository;
//    @Autowired
//    private IOrderService iOrderService;

    @Autowired
    private Producer producer;
    @Autowired
    private IOrderRepository iOrderRepository;

    @Override
    public List<User> getAllUser() {
        return iUserRepository.findAll();
    }

    @Override
    public User getUserDetails(String email){
        User user=iUserRepository.findById(email).get();
        user.setProfileImage(ImageUtils.decompressImage(user.getProfileImage()));
        return user;
    }

    @Override
    public List<Order> getUserOrderDetails(String email)throws UserNotFoundExceptions {
        if(iUserRepository.findById(email).isPresent()) throw new UserNotFoundExceptions();
        return iUserRepository.findById(email).get().getOrders();
    }

    @Override
    public Set<String> getUserWishlistAllCuisines(String email) {
        return iUserRepository.findById(email).get().getWishLists().getCuisines();
    }

    @Override
    public Map<String, String> getUserWishListAllRestaurants(String email) {
        return iUserRepository.findById(email).get().getWishLists().getRestaurants();
    }

    @Override
    public List<Address> getUserAllAddress(String email) {
        return iUserRepository.findById(email).get().getAddresses();
    }

    @Override
    public User addUser(User user) {
        user.setProfileImage(iUserRepository.findById("123").get().getProfileImage());
        return iUserRepository.insert(user);
    }

    @Override
    public List<Address> addUserAddress(String email, Address address) {
        User user=iUserRepository.findById(email).get();
        user.getAddresses().add(address);
        return iUserRepository.save(user).getAddresses() ;

    }

    @Override
    public Order addOrderToUser(String email, Order order)
    {
        order.setUserId(email);
        Order order1=iOrderRepository.insert(order);
        User user=iUserRepository.findById(email).get();
        user.getOrders().add(order1);
        User user1=iUserRepository.save(user);
        System.out.println(order1);
        producer.orderStatusToQueue(order1);
        return order1;
    }

    @Override
    public Set<String> addCuisinesToUserWishlist(String email, String cuisine)
    {
        User user=iUserRepository.findById(email).get();
        user.getWishLists().getCuisines().add(cuisine);
        return iUserRepository.save(user).getWishLists().getCuisines();
    }

    @Override
    public Map<String, String> addRestaurantToUserWishlist(String email, String restaurantName, String restaurantId)
    {
        User user=iUserRepository.findById(email).get();
        user.getWishLists().getRestaurants().put(restaurantId,restaurantName);
        return iUserRepository.save(user).getWishLists().getRestaurants();
    }

    @Override
    public User updateUserProfileImage(String email, MultipartFile profileImage) throws IOException {
        User user=iUserRepository.findById(email).get();
        user.setProfileImage(ImageUtils.compressImage(profileImage.getBytes()));
        User user1=iUserRepository.save(user);
        user1.setProfileImage(ImageUtils.decompressImage(user1.getProfileImage()));
        return user1;
    }

    @Override
    public List<Address> updateUserAddress(String email, String orderPlace, Address address) {
        User user=iUserRepository.findById(email).get();
        System.out.println("service user"+user);
        System.out.println("service email"+email);
        for (Address addressTemp:user.getAddresses())
        {
            if (addressTemp.getOrderPlace().equalsIgnoreCase(orderPlace))
            {
                addressTemp.setCity(address.getCity());
                addressTemp.setStreet(address.getStreet());
                addressTemp.setDoorNo(address.getDoorNo());
                addressTemp.setState(address.getState());
                addressTemp.setZipcode(address.getZipcode());
                addressTemp.setOrderPlace(address.getOrderPlace());
                return iUserRepository.save(user).getAddresses();
            }
        }
        return null;
    }

    @Override
    public Order updateOrderStatusToUser(String email, String orderId, String orderStatus)
    {
        User user=iUserRepository.findById(email).get();

        for (Order order:user.getOrders())
        {
            if (order.getOrderId().equalsIgnoreCase(orderId))
            {
                order.setOrderStatus(orderStatus);
                iUserRepository.save(user);
                if(order.getOrderStatus().equalsIgnoreCase("DELIVERED"))
                {
                    String body="Your Order is Delivered";
                    producer.sendMailDtoToQueue(new EmailDTO(order.getUserId(),body,"ORDER DELIVERED"));
                }
                return order;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(String email)throws UserNotFoundExceptions
    {
        if(iUserRepository.findById(email).isEmpty()) throw new UserNotFoundExceptions();
        iUserRepository.deleteById(email);
    }

    @Override
    public List<Address> deleteAddressFromUser(String email, String orderPlace)
    {
        User user=iUserRepository.findById(email).get();
        for (Address address:user.getAddresses())
        {
            if (address.getOrderPlace().equalsIgnoreCase(orderPlace))
            {
                user.getAddresses().remove(address);
                return iUserRepository.save(user).getAddresses();
            }
        }
        return null;
    }

    @Override
    public Set<String> deleteCuisineFromUserWishlist(String email, String cuisineName)
    {
        User user=iUserRepository.findById(email).get();
        for (String cuisine:user.getWishLists().getCuisines())
        {
            if (cuisine.equalsIgnoreCase(cuisineName))
            {
                user.getWishLists().getCuisines().remove(cuisine);
                return iUserRepository.save(user).getWishLists().getCuisines();
            }
        }
        return null;
    }

    @Override
    public Map<String, String> deleteRestaurantFromUserWishlist(String email, String restaurantName)
    {
        User user=iUserRepository.findById(email).get();
        for (Map.Entry<String,String> restaurant:user.getWishLists().getRestaurants().entrySet())
        {
            if (restaurant.getValue().equalsIgnoreCase(restaurantName))
            {
            user.getWishLists().getRestaurants().remove(restaurant.getKey());
            return iUserRepository.save(user).getWishLists().getRestaurants();
            }
        }
        return null;
    }
}
