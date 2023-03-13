package com.project.SellerServiceApp.repository;

import com.project.SellerServiceApp.domain.Restaurant;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IRestaurantRepository extends MongoRepository<Restaurant,String>
{
    public List<Restaurant> findByRestaurantName(String restaurantName);

    @Aggregation(pipeline = {
            "{ '$match' : { 'address.city' : { '$ne' : null } } }",
            "{ '$group' : { '_id' : '$address.city' } }"
    })
    Set<String> findDistinctCityNames();
}
