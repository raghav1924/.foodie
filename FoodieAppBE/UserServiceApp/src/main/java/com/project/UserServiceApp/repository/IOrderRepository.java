package com.project.UserServiceApp.repository;

import com.project.UserServiceApp.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends MongoRepository<Order,String> {
}
