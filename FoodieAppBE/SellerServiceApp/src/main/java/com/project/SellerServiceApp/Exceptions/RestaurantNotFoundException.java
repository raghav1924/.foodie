package com.project.SellerServiceApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Restaurant Not Found")
public class RestaurantNotFoundException extends Exception {
}
