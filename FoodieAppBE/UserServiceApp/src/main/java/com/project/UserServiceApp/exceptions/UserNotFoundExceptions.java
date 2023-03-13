package com.project.UserServiceApp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "User Not Found")
public class UserNotFoundExceptions extends Exception
{

}
