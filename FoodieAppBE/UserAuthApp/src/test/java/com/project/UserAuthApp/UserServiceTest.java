package com.project.UserAuthApp;

import com.project.UserAuthApp.domain.User;
import com.project.UserAuthApp.domain.UserSignUp;
import com.project.UserAuthApp.exceptions.ContactAlreadyRegistered;
import com.project.UserAuthApp.exceptions.EmailAlreadyRegistered;
import com.project.UserAuthApp.exceptions.InvalidCredentials;
import com.project.UserAuthApp.exceptions.UserNotFoundException;
import com.project.UserAuthApp.proxy.UserProxy;
import com.project.UserAuthApp.rabbitmq.EmailDTO;
import com.project.UserAuthApp.rabbitmq.MailProducer;
import com.project.UserAuthApp.repository.IUserRepository;
import com.project.UserAuthApp.services.CUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository iUserRepository;

    @Mock
    private UserProxy userProxy;

    @Mock
    private MailProducer mailProducer;
    @InjectMocks
    private CUserService cUserService;

    private User user;
    private UserSignUp userSignUp;
    private EmailDTO emailDTO;

    private List<User> userList;

    @BeforeEach
    public void setUp()
    {
        user=new User( "user123@gmail.com","ROLE_USER","123","1234567890");
        userSignUp=new UserSignUp();
        userSignUp.setEmail("user123@gmail.com");
        userSignUp.setName("user");
        userSignUp.setPhoneNo("1234567890");
        userSignUp.setPassword("123");
        userList=new ArrayList<User>();
        this.userList.add(user);

        emailDTO=new EmailDTO("user123@gmail.com","You Have Successfully Registered To Foodie App.... \n Thank You For Using Our Services!!!","REGISTRATION SUCCESSFUL");
    }
    @AfterEach
    public void clear()
    {
        user=null;
        userSignUp=null;
    }

    @Test
    public void registerUserSuccess()throws EmailAlreadyRegistered, ContactAlreadyRegistered
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(ofNullable(null));
        when(userProxy.sendDataToUserServiceApp(any())).thenReturn(any());
        when(iUserRepository.save(user)).thenReturn(user);
        doNothing().when(mailProducer).sendMailDtoToQueue(emailDTO);
        assertEquals(user, cUserService.userRegistration(userSignUp));
        verify(iUserRepository,times(1)).save(any());
        verify(iUserRepository,times(2)).findById(any());
    }

    @Test
    public void registreUserFailure()
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(ofNullable(user));
        assertThrows(EmailAlreadyRegistered.class,()->cUserService.userRegistration(userSignUp));
    }
    @Test
    public void userLogInSuccess() throws InvalidCredentials {
        when(iUserRepository.findById(user.getEmail())).thenReturn(ofNullable(user));
        assertEquals(user, cUserService.userLogIn(user));
        verify(iUserRepository,times(2)).findById(user.getEmail());
    }

    @Test
    public  void userLogInFailure()
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(ofNullable(null));
        assertThrows(InvalidCredentials.class,()->cUserService.userLogIn(user));
    }

    @Test
    public void deleteUserSuccess() {
        when(iUserRepository.findById(user.getEmail())).thenReturn(ofNullable(null));
        iUserRepository.delete(user);
        assertEquals(Optional.empty(), iUserRepository.findById(user.getEmail()));
    }
    @Test
    public void deleteUserFailure()
    {
        when(iUserRepository.findById(user.getEmail())).thenReturn(ofNullable(null));
        assertThrows(UserNotFoundException.class,()->cUserService.deleteUser(user.getEmail()));
    }
}
