package com.project.SellerAuthApp.repository;

import com.project.SellerAuthApp.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,String> {
    public Seller findByEmailAndPassword(String email,String password);
}
