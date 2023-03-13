package com.project.SellerServiceApp;

import com.project.SellerServiceApp.Filter.CFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class SellerServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerServiceAppApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<?> filterUrl(){
		System.out.println("This is about Filter");
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CFilter());
//		filterRegistrationBean.addUrlPatterns("/sellerService/*");
//		filterRegistrationBean.addUrlPatterns("/restaurantService/*");
		filterRegistrationBean.addUrlPatterns("/sellerService/getSellerAllRestaurant");
		filterRegistrationBean.addUrlPatterns("/sellerService/getSellerDetails");
		filterRegistrationBean.addUrlPatterns("/sellerService/addRestaurantToSeller");
//		filterRegistrationBean.addUrlPatterns("/restaurantService/getRestaurantDetails/*");
		filterRegistrationBean.addUrlPatterns("/restaurantService/getRestaurantDetailsByName/*");
		filterRegistrationBean.addUrlPatterns("/restaurantService/getAllCuisines/*");
		filterRegistrationBean.addUrlPatterns("/restaurantService/getFoodItems/**");
		filterRegistrationBean.addUrlPatterns("/restaurantService/updateRestaurantImage/*");
		filterRegistrationBean.addUrlPatterns("/restaurantService/updateFoodItemImage/*");
		filterRegistrationBean.addUrlPatterns("/restaurantService/updateFoodItem/**");
		filterRegistrationBean.addUrlPatterns("/restaurantService/addCuisineToList/*");
		filterRegistrationBean.addUrlPatterns("/restaurantService/addFoodItem/**");
		filterRegistrationBean.addUrlPatterns("/restaurantService/addRestaurant");
		filterRegistrationBean.addUrlPatterns("/restaurantService/deleteCuisine/**");
		filterRegistrationBean.addUrlPatterns("/restaurantService/deleteFoodItem/**");
		return filterRegistrationBean;
	}

}
