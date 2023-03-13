package com.project.UserAuthApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "This user Not Available")
public class UserNotFoundException extends Exception {
}
