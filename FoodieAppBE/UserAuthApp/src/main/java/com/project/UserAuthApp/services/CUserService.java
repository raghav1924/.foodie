package com.project.UserAuthApp.services;

import com.project.UserAuthApp.domain.User;
import com.project.UserAuthApp.domain.UserDTO;
import com.project.UserAuthApp.domain.UserSignUp;
import com.project.UserAuthApp.exceptions.ContactAlreadyRegistered;
import com.project.UserAuthApp.exceptions.EmailAlreadyRegistered;
import com.project.UserAuthApp.exceptions.InvalidCredentials;
import com.project.UserAuthApp.exceptions.UserNotFoundException;
import com.project.UserAuthApp.proxy.UserProxy;
import com.project.UserAuthApp.rabbitmq.EmailDTO;
import com.project.UserAuthApp.rabbitmq.MailProducer;
import com.project.UserAuthApp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.concurrent.ThreadLocalRandom;

@Service
public class CUserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private MailProducer mailProducer;

    @Autowired
    private UserProxy userProxy;

    @Override
    public int generateOTP()
    {
        int otp= ThreadLocalRandom.current().nextInt(1000,9999);
        return otp;
    }

    @Override
    public int sendOTP(String email)
    {
        int otp=generateOTP();
        String mailBodyWithOTP="Foodie App OTP Verification "+otp;
        EmailDTO emailDTO=new EmailDTO(email,mailBodyWithOTP,"OTP");
        mailProducer.sendMailDtoToQueue(emailDTO);
        System.out.println(emailDTO);
        return otp;
    }

    @Override
    public User userRegistration(UserSignUp userSignUp)throws EmailAlreadyRegistered, ContactAlreadyRegistered {
        if(iUserRepository.findById(userSignUp.getEmail()).isPresent())
            throw new EmailAlreadyRegistered();
//        if(iUserRepository.findById(userSignUp.getPhoneNo()).isPresent())
//            throw new ContactAlreadyRegistered();
        userProxy.sendDataToUserServiceApp(new UserDTO(userSignUp.getEmail(),userSignUp.getPhoneNo(),userSignUp.getName()));
        User user=iUserRepository.save(new User(userSignUp.getEmail(),userSignUp.getPassword(),"ROLE_USER",userSignUp.getPhoneNo()));
        mailProducer.sendMailDtoToQueue(new EmailDTO(user.getEmail(),"You Have Successfully Registered To Foodie App.... \n Thank You For Using Our Services!!!","REGISTRATION SUCCESSFUL"));
        return user;
    }

    @Override
    public User userLogIn(User user)throws InvalidCredentials {
        if(iUserRepository.findById(user.getEmail()).isPresent())
        {
             User user1=iUserRepository.findById(user.getEmail()).get();
             if(user1.getPassword().equals(user.getPassword()))
             {
                 return user1;
             }
        }else throw new InvalidCredentials();
        return null;
    }

    @Override
    public void deleteUser(String email)throws UserNotFoundException {
        if(iUserRepository.findById(email).isPresent())
        {
            iUserRepository.deleteById(email);
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
