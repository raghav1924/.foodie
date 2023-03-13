package com.project.UserAuthApp;

import com.project.UserAuthApp.domain.User;
import com.project.UserAuthApp.repository.IUserRepository;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private IUserRepository iUserRepository;

    private User user;


    @BeforeEach
    public void setUp(){
        user=new User("user123@gmail.com","123","USER_ROLE","8585968574");
    }
    @AfterEach
    public void tearDown()
    {
        user=null;
    }

    @Test
    @DisplayName("User regitration Success")
    public void auserRegistrationSuccess()
    {
        iUserRepository.save(user);
        User user1 = iUserRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertEquals(user.getEmail(), user1.getEmail());
    }
    @Test
    @DisplayName("User regitration failure")
    public void buserRegistrationFailure()
    {
        iUserRepository.save(user);
        User user1 = iUserRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertNotEquals(user, user1.getEmail());
    }
    @Test
    @DisplayName("user Log In Success")
    public void cuserLogInSuccess()
    {
        User user1=iUserRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertEquals(user.getEmail(), user1.getEmail());
        assertEquals(user.getPassword(),user1.getPassword());
    }

    @Test
    @DisplayName("user Log In Failure")
    public void userLogInFailure()
    {
        User user1=iUserRepository.findById(user.getEmail()).get();
        if(user1.getEmail()==null)
        {
            assertNotNull(user1);
            assertNotEquals(user.getEmail(), user1.getEmail());
        }

    }
    @Test
    @DisplayName("Delete User Success")
    public void fdeleteUserSuccess()
    {
        User user1=iUserRepository.findById(user.getEmail()).get();
        iUserRepository.delete(user);
        assertEquals(Optional.empty(), iUserRepository.findById(user.getEmail()));
    }

    @Test
    @DisplayName("Delete user Failure")
    public void edeleteUserFailure()
    {
        iUserRepository.save(user);
        User user1=iUserRepository.findById(user.getEmail()).get();
        iUserRepository.deleteById(user1.getEmail());
        assertNotEquals(user, iUserRepository.findById(user.getEmail()));
    }
}
