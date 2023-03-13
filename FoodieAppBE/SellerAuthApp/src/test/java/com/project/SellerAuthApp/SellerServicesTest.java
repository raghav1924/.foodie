package com.project.SellerAuthApp;

import com.project.SellerAuthApp.Exceptions.ContactAlreadyRegistered;
import com.project.SellerAuthApp.Exceptions.EmailAlreadyRegistered;
import com.project.SellerAuthApp.Exceptions.InvalidCredentials;
import com.project.SellerAuthApp.Exceptions.SellerNotFoundException;
import com.project.SellerAuthApp.domain.Seller;
import com.project.SellerAuthApp.domain.SellerDTO;
import com.project.SellerAuthApp.domain.SellerSignUp;
import com.project.SellerAuthApp.proxy.SellerProxy;
import com.project.SellerAuthApp.rabbitmq.EmailDTO;
import com.project.SellerAuthApp.rabbitmq.MailProducer;
import com.project.SellerAuthApp.repository.SellerRepository;
import com.project.SellerAuthApp.services.SellerSService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SellerServicesTest {
    @Mock
    private SellerRepository sellerRepository;

   @Mock
   private SellerProxy sellerProxy;

   @Mock
   private MailProducer mailProducer;
    @InjectMocks
    private SellerSService sellerSService;

    private Seller seller;
    private SellerSignUp sellerSignUp;
    private EmailDTO emailDTO;

    private List<Seller> sellerList;




    @BeforeEach
    public void setUp()
    {
        seller=new Seller( "seller123@gmail.com","ROLE_SELLER","123","1234567890");
        sellerSignUp=new SellerSignUp();
        sellerSignUp.setEmail("seller123@gmail.com");
        sellerSignUp.setName("seller");
        sellerSignUp.setPhoneNo("1234567890");
        sellerSignUp.setPassword("123");
        sellerSignUp.setConfirmPassword("123");
        sellerList=new ArrayList<Seller>();
        this.sellerList.add(seller);

        emailDTO=new EmailDTO("seller123@gmail.com","You Have Successfully Registered To Foodie App.... \n Thank You For Using Our Services!!!","REGISTRATION SUCCESSFUL");
    }
    @AfterEach
    public void clear()
    {
        seller=null;
        sellerSignUp=null;
    }

    @Test
    public void registerSellerSuccess()throws EmailAlreadyRegistered, ContactAlreadyRegistered
    {
        when(sellerRepository.findById(seller.getEmail())).thenReturn(ofNullable(null));
        when(sellerProxy.sendDataToSellerServiceApp(any())).thenReturn(any());
        when(sellerRepository.save(seller)).thenReturn(seller);
        doNothing().when(mailProducer).sendMailDtoToQueue(emailDTO);
        assertEquals(seller, sellerSService.sellerRegistration(sellerSignUp));
        verify(sellerRepository,times(1)).save(any());
        verify(sellerRepository,times(2)).findById(any());
    }

    @Test
    public void registreSellerFailure()
    {
        when(sellerRepository.findById(seller.getEmail())).thenReturn(ofNullable(seller));
        assertThrows(EmailAlreadyRegistered.class,()->sellerSService.sellerRegistration(sellerSignUp));
    }
    @Test
    public void sellerLogInSuccess() throws  InvalidCredentials {
            when(sellerRepository.findByEmailAndPassword(seller.getEmail(),seller.getPassword())).thenReturn(seller);
            assertEquals(seller, sellerSService.sellerLogIn(seller));
            verify(sellerRepository,times(1)).findByEmailAndPassword(seller.getEmail(),seller.getPassword());
    }

    @Test
    public  void sellerLogInFailure()
    {
        when(sellerRepository.findByEmailAndPassword(seller.getEmail(),seller.getPassword())).thenReturn(null);
            assertThrows(InvalidCredentials.class,()->sellerSService.sellerLogIn(seller));
    }

    @Test
    public void deleteSellerSuccess() {
        when(sellerRepository.findById(seller.getEmail())).thenReturn(ofNullable(null));
        sellerRepository.delete(seller);
        assertEquals(Optional.empty(), sellerRepository.findById(seller.getEmail()));
    }
   @Test
    public void deleteSellerFailure()
   {
       when(sellerRepository.findById(seller.getEmail())).thenReturn(ofNullable(null));
       assertThrows(SellerNotFoundException.class,()->sellerSService.deleteSeller(seller.getEmail()));
   }

}
