package com.project.SellerAuthApp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.SellerAuthApp.Exceptions.ContactAlreadyRegistered;
import com.project.SellerAuthApp.Exceptions.InvalidCredentials;
import com.project.SellerAuthApp.Exceptions.SellerNotFoundException;
import com.project.SellerAuthApp.controller.SellerController;
import com.project.SellerAuthApp.domain.Seller;
import com.project.SellerAuthApp.domain.SellerSignUp;
import com.project.SellerAuthApp.services.SellerSService;
import com.project.SellerAuthApp.services.SellerService;
import com.project.SellerAuthApp.services.Token.SellerToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SellerControllerTest {

    @Mock
    private SellerSService sellerSService;
    @InjectMocks
    private SellerController sellerController;
    @Autowired
    private MockMvc mockMvc;

    private Seller seller;
    private SellerSignUp sellerSignUp;

    @Mock
    private SellerToken sellerToken;


    private Map<String,String> result;

    @BeforeEach
    void setUp()
    {
        sellerSignUp =new SellerSignUp("seller123@gmail.com","123", "ROLE_SELLER","1234567890","seller","123");
        seller = new Seller("seller123@gmail.com", "123","ROLE_SELLER","7894561230");
        result=new HashMap<>();
        result.put("token","abcd");
        result.put("message","seller login success");
        result.put("role",seller.getRole());
        result.put("seller_email",seller.getEmail());
        mockMvc = MockMvcBuilders.standaloneSetup(sellerController).build();
    }
    @AfterEach
    void clear() {
        this.seller = null;
        this.sellerSignUp=null;
    }
    @Test
    public void sellerRegistrationSucces() throws Exception
    {
        when(sellerSService.sellerRegistration(sellerSignUp)).thenReturn(seller);
        mockMvc.perform(post("/sellerAuth/sellerRegistration").contentType(APPLICATION_JSON).content(jsToString(sellerSignUp)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void sellerRegistrationFailure() throws Exception
    {
        when(sellerSService.sellerRegistration(any())).thenThrow(ContactAlreadyRegistered.class);
        mockMvc.perform(post("/sellerAuth/sellerRegistration").contentType(APPLICATION_JSON).content(jsToString(sellerSignUp)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(sellerSService,times(1)).sellerRegistration(any());
    }
    @Test
    public void sellerLogInSuccess() throws Exception
    {
        when(sellerSService.sellerLogIn(seller)).thenReturn(seller);
        when(sellerToken.sellerGenerateToken(seller)).thenReturn(result);
        mockMvc.perform(post("/sellerAuth/sellerLogIn").contentType(APPLICATION_JSON).content(jsToString(seller)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(sellerSService,times(1)).sellerLogIn(seller);
    }

    @Test
    public void sellerLogInFailure() throws Exception {
        when(sellerSService.sellerLogIn(any())).thenThrow(InvalidCredentials.class);
        mockMvc.perform(post("/sellerAuth/sellerLogIn").contentType(APPLICATION_JSON).content(jsToString(seller)))
                .andExpect(status().isUnauthorized()).andDo(MockMvcResultHandlers.print());
        verify(sellerSService,times(1)).sellerLogIn(any());
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
