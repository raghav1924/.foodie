package com.project.SellerAuthApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "This Seller Not Available")
public class SellerNotFoundException extends Exception{
}
