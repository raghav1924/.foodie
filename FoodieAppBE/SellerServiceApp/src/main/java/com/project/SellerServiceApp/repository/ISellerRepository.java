package com.project.SellerServiceApp.repository;

import com.project.SellerServiceApp.domain.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISellerRepository extends MongoRepository<Seller,String>
{

}
