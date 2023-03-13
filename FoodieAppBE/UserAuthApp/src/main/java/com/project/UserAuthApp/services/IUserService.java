package com.project.UserAuthApp.services;

import com.project.UserAuthApp.domain.User;
import com.project.UserAuthApp.domain.UserSignUp;
import com.project.UserAuthApp.exceptions.ContactAlreadyRegistered;
import com.project.UserAuthApp.exceptions.EmailAlreadyRegistered;
import com.project.UserAuthApp.exceptions.InvalidCredentials;
import com.project.UserAuthApp.exceptions.UserNotFoundException;

public interface IUserService {
    public int generateOTP();
    public int sendOTP(String email);
    public User userRegistration(UserSignUp userSignUp)throws EmailAlreadyRegistered, ContactAlreadyRegistered;
    public User userLogIn(User user)throws InvalidCredentials;
    public void deleteUser(String email)throws UserNotFoundException;
}
