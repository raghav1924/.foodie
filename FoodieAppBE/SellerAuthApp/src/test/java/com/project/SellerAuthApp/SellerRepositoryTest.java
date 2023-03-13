package com.project.SellerAuthApp;

import com.project.SellerAuthApp.domain.Seller;
import com.project.SellerAuthApp.domain.SellerDTO;
import com.project.SellerAuthApp.domain.SellerSignUp;
import com.project.SellerAuthApp.repository.SellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository sellerRepository;

    private Seller seller;
    private SellerSignUp sellerSignUp;
    private SellerDTO sellerDTO;

    @BeforeEach
    public void setUp(){
        seller=new Seller("deshmukhdivya0506@gmail.com","123","SELLER_ROLE","8585968574");
    }
    @AfterEach
    public void tearDown()
    {
        seller=null;
    }

    @Test
    @DisplayName("Test case for seller regitration Success")
    public void asellerRegistrationSucces()
    {
        sellerRepository.save(seller);
        Seller seller1 = sellerRepository.findById(seller.getEmail()).get();
        assertNotNull(seller1);
        assertEquals(seller.getEmail(), seller1.getEmail());
    }
    @Test
    @DisplayName("Test case for seller regitration failure")
    public void bsellerRegistrationFailure()
    {
        sellerRepository.save(seller);
        Seller seller1 = sellerRepository.findById(seller.getEmail()).get();
        assertNotNull(seller1);
        assertNotEquals(seller, seller1.getEmail());
    }

    @Test
    @DisplayName("Seller Log In Success")
    public void csellerLogInSucces()
    {
        Seller seller1=sellerRepository.findByEmailAndPassword(seller.getEmail(),seller.getPassword());
        assertNotNull(seller1);
        assertEquals(seller.getEmail(), seller1.getEmail());
        assertEquals(seller.getPassword(),seller1.getPassword());
    }
    @Test
    @DisplayName("Seller Log In Failure")
    public void sellerLogInFailure()
    {
        Seller seller1=sellerRepository.findByEmailAndPassword(seller.getEmail(),seller.getPassword());
        if(seller1.getEmail()==null)
        {
            assertNotNull(seller1);
            assertNotEquals(seller.getEmail(), seller1.getEmail());
            assertNotEquals(seller.getPassword(),seller1.getPassword());
        }

    }

    @Test
    @DisplayName("Delete Seller Success")
    public void edeleteSellerSccess()
    {
        Seller seller1=sellerRepository.findById(seller.getEmail()).get();
        sellerRepository.delete(seller);
        assertEquals(Optional.empty(), sellerRepository.findById(seller.getEmail()));
    }

    @Test
    @DisplayName("Delete Seller Failure")
    public void fdeleteSellerFailure()
    {
        sellerRepository.save(seller);
        Seller seller1=sellerRepository.findById(seller.getEmail()).get();
        sellerRepository.deleteById(seller1.getEmail());
        assertNotEquals(seller, sellerRepository.findById(seller.getEmail()));
    }
}
