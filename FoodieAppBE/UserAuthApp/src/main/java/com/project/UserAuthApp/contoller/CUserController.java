package com.project.UserAuthApp.contoller;

import com.project.UserAuthApp.domain.User;
import com.project.UserAuthApp.domain.UserSignUp;
import com.project.UserAuthApp.exceptions.ContactAlreadyRegistered;
import com.project.UserAuthApp.exceptions.EmailAlreadyRegistered;
import com.project.UserAuthApp.exceptions.InvalidCredentials;
import com.project.UserAuthApp.services.IUserService;
import com.project.UserAuthApp.services.IUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAuth/")
public class CUserController
{
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IUserToken iUserToken;

    //http://localhost:9999/userAuth/otp
    @PostMapping("/otp")
    public ResponseEntity<?> getOtp(@RequestBody String email)
    {
        System.out.println(email);
        return new ResponseEntity<>(iUserService.sendOTP(email), HttpStatus.OK);
    }
    @PostMapping("/userRegistration")
    public ResponseEntity<?> userRegistration(@RequestBody UserSignUp userSignUp) throws EmailAlreadyRegistered, ContactAlreadyRegistered {
        return new ResponseEntity<>(iUserService.userRegistration(userSignUp), HttpStatus.CREATED);
    }
    @PostMapping("/userLogIn")
    public ResponseEntity<?> userLogIn(@RequestBody User user) throws InvalidCredentials {
        User checkedUserLogIn=iUserService.userLogIn(user);
        if(checkedUserLogIn!=null)
        {
            return new ResponseEntity<>(iUserToken.userGenerateToken(checkedUserLogIn),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Authentication Failed....",HttpStatus.NOT_FOUND);
        }
    }
}
