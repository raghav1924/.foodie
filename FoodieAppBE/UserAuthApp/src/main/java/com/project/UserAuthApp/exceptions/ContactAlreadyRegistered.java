package com.project.UserAuthApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Contact Number Already Exists")

public class ContactAlreadyRegistered extends Exception{
}
