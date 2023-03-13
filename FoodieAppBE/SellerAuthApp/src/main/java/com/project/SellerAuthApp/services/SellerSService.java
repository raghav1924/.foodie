package com.project.SellerAuthApp.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class SellerSService implements SellerService{

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private MailProducer mailProducer;
    @Autowired
    private SellerProxy sellerProxy;
    @Override
    public int generateOTP() {
        int otp= ThreadLocalRandom.current().nextInt(1000,9999);
        return otp;
    }

    @Override
    public int sendOTP(String email)
    {
        int otp=generateOTP();
        String otp1="OTP For Register Restuarant in Foodie App "+otp;
        EmailDTO emailDTO=new EmailDTO(email,otp1,"OTP");
        mailProducer.sendMailDtoToQueue(emailDTO);
        System.out.println(emailDTO);
        return otp;
    }

    @Override
    public Seller sellerRegistration(SellerSignUp sellerSignUp)throws EmailAlreadyRegistered , ContactAlreadyRegistered  {
        if(sellerRepository.findById(sellerSignUp.getEmail()).isPresent()) throw new EmailAlreadyRegistered();
        if(sellerRepository.findById(sellerSignUp.getPhoneNo()).isPresent()) throw new ContactAlreadyRegistered();

        sellerProxy.sendDataToSellerServiceApp(new SellerDTO(sellerSignUp.getEmail(),sellerSignUp.getPhoneNo(),sellerSignUp.getName()));
        Seller seller=sellerRepository.save(new Seller(sellerSignUp.getEmail(),sellerSignUp.getPassword(),"ROLE_SELLER",sellerSignUp.getPhoneNo()));
        mailProducer.sendMailDtoToQueue(new EmailDTO(seller.getEmail(),"You Have Successfully Registered To Foodie App.... \n Thank You For Using Our Services!!!","REGISTRATION SUCCESSFUL"));
        return seller;
    }

    @Override
    public Seller sellerLogIn(Seller seller) throws InvalidCredentials {
        Seller seller1=sellerRepository.findByEmailAndPassword(seller.getEmail(),seller.getPassword());
        if(seller1==null) throw new InvalidCredentials();
        else return seller1;
    }

    @Override
    public void deleteSeller(String email)throws SellerNotFoundException {
        if(sellerRepository.findById(email).isPresent())
        {
            sellerRepository.deleteById(email);
        }
        else {
            throw new SellerNotFoundException();
        }
    }
}
