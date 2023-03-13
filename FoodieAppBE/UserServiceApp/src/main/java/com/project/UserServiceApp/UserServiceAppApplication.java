package com.project.UserServiceApp;

import com.project.UserServiceApp.Filter.CUserFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceAppApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<?> filterUrl() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CUserFilter());
//		filterRegistrationBean.addUrlPatterns("/userService/getAllUser");
		filterRegistrationBean.addUrlPatterns("/userService/getUserDetails");
		filterRegistrationBean.addUrlPatterns("/userService/getUserOrderDetails");
		filterRegistrationBean.addUrlPatterns("/userService/getUserWishlistAllCuisines");
		filterRegistrationBean.addUrlPatterns("/userService/getUserWishListAllRestaurants");
		filterRegistrationBean.addUrlPatterns("/userService/getUserAllAddress");
		filterRegistrationBean.addUrlPatterns("/userService/addUserAddress");
		filterRegistrationBean.addUrlPatterns("/userService/addOrderToUser");
		filterRegistrationBean.addUrlPatterns("/userService/addCuisinesToUserWishlist");
		filterRegistrationBean.addUrlPatterns("/userService/addRestaurantToUserWishlist/*");
		filterRegistrationBean.addUrlPatterns("/userService/updateUserProfileImage");
		filterRegistrationBean.addUrlPatterns("/userService/updateUserAddress/*");
		filterRegistrationBean.addUrlPatterns("/userService/updateOrderStatusToUser/*");
		filterRegistrationBean.addUrlPatterns("/userService/deleteUser");
		filterRegistrationBean.addUrlPatterns("/userService/deleteAddressFromUser/*");
		filterRegistrationBean.addUrlPatterns("/userService/deleteCuisineFromUserWishlist");
		filterRegistrationBean.addUrlPatterns("/userService/deleteRestaurantFromUserWishlist/*");

		return filterRegistrationBean;
	}
}
