package com.project.UserAuthApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.UserAuthApp.contoller.CUserController;
import com.project.UserAuthApp.domain.User;
import com.project.UserAuthApp.domain.UserSignUp;
import com.project.UserAuthApp.exceptions.ContactAlreadyRegistered;
import com.project.UserAuthApp.exceptions.InvalidCredentials;
import com.project.UserAuthApp.services.CUserService;
import com.project.UserAuthApp.services.IUserToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private CUserService cUserService;
    @InjectMocks
    private CUserController cUserController;
    @Autowired
    private MockMvc mockMvc;

    private User user;
    private UserSignUp userSignUp;

    @Mock
    private IUserToken iUserToken;


    private Map<String,String> result;

    @BeforeEach
    void setUp()
    {
        userSignUp =new UserSignUp("user123@gmail.com","123", "ROLE_USER","1234567890","user");
        user = new User("user123@gmail.com", "123","ROLE_USER","1234567890");
        result=new HashMap<>();
        result.put("token","abcd");
        result.put("message","seller login success");
        result.put("role",user.getRole());
        result.put("seller_email",user.getEmail());
        mockMvc = MockMvcBuilders.standaloneSetup(cUserController).build();
    }
    @AfterEach
    void clear() {
        this.user = null;
        this.userSignUp=null;
    }

    @Test
    public void userRegistrationSucces() throws Exception
    {
        when(cUserService.userRegistration(userSignUp)).thenReturn(user);
        mockMvc.perform(post("/userAuth/userRegistration").contentType(APPLICATION_JSON).content(jsToString(userSignUp)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void userRegistrationFailure() throws Exception
    {
        when(cUserService.userRegistration(any())).thenThrow(ContactAlreadyRegistered.class);
        mockMvc.perform(post("/userAuth/userRegistration").contentType(APPLICATION_JSON).content(jsToString(userSignUp)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(cUserService,times(1)).userRegistration(any());
    }

    @Test
    public void userLogInSuccess() throws Exception
    {
        when(cUserService.userLogIn(user)).thenReturn(user);
        when(iUserToken.userGenerateToken(user)).thenReturn(result);
        mockMvc.perform(post("/userAuth/userLogIn").contentType(APPLICATION_JSON).content(jsToString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(cUserService,times(1)).userLogIn(user);
    }

    @Test
    public void userLogInFailure() throws Exception {
        when(cUserService.userLogIn(any())).thenThrow(InvalidCredentials.class);
        mockMvc.perform(post("/userAuth/userLogIn").contentType(APPLICATION_JSON).content(jsToString(user)))
                .andExpect(status().isUnauthorized()).andDo(MockMvcResultHandlers.print());
        verify(cUserService,times(1)).userLogIn(any());
    }








    private static String jsToString(Object ob)
    {
        String result;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }
}
