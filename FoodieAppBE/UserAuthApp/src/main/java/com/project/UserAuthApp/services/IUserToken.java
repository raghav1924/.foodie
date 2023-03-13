package com.project.UserAuthApp.services;

import com.project.UserAuthApp.domain.User;

import java.util.Map;

public interface IUserToken {
    public Map<String,String> userGenerateToken(User user);
}
