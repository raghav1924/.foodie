import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FoodItems } from '../model/FoodItems';
import { Order } from '../model/Order';
import { Restaurant } from '../model/Seller/Restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantDBService {
    activatedRoute: any;

  constructor(private httpclient:HttpClient){}

  RestaurantDBUrl = "http://localhost:5555/restaurantService";

  getRestaurantDetails(restaurantId:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/getRestaurantDetails/"+restaurantId,requestOptions);
  }


  getRestaurantDetailsByName(restaurantName:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/getRestaurantDetailsByName/"+restaurantName,requestOptions);
  }

  getAllRestaurant()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/getAllRestaurant",requestOptions);
  }

  getAllCuisines(restaurantId:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/getAllCuisines/"+this.restaurantId,requestOptions);
  }

  getFoodItems(restaurantId:string,cuisineName:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/getFoodItems/"+restaurantId+"/"+cuisineName,requestOptions);
  }

  getAllRestaurantsByCity(city:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/getAllRestaurantsByCity/"+city,requestOptions);
  }

  getAllRestaurantsByCuisine(cuisineName:any,city:any)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/getAllRestaurantsByCuisine/"+cuisineName+"/"+city,requestOptions);
  }

  updateRestaurantName(restaurant:Restaurant)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.put(this.RestaurantDBUrl+"/updateRestaurantName",restaurant,requestOptions);
  }

  updateRestaurantImage(restaurantId:string,imageData:FormData)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.put(this.RestaurantDBUrl+"/updateRestaurantImage/"+restaurantId,imageData,requestOptions);
  }

  updateFoodItem(restaurantId:any,cuisineName:string,foodItem:any)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.put(this.RestaurantDBUrl+"/updateFoodItem/"+restaurantId+"/"+cuisineName,foodItem,requestOptions);
  }
  updateFoodItemImage(restaurantId:any,cuisineName:string,foodItemName:any,foodItemImage:FormData)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.put(this.RestaurantDBUrl+"/updateFoodItemImage/"+restaurantId+"/"+cuisineName+"/"+foodItemName,foodItemImage,requestOptions);
  }

  addCuisineToList(restaurantId:any,cuisineName:any)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      console.log(restaurantId)
      return this.httpclient.post(this.RestaurantDBUrl+"/addCuisineToList/"+restaurantId,cuisineName,requestOptions);
  }

  addRestaurant(restaurant:any)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.post(this.RestaurantDBUrl+"/addRestaurant",restaurant,requestOptions);
  }

  addFoodItem(restaurantId:any,cuisineName:any,foodItems:any)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.post(this.RestaurantDBUrl+"/addFoodItem/"+restaurantId+"/"+cuisineName,foodItems,requestOptions);
  }

  deleteCuisine(restaurantId:string,cuisineName:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.delete(this.RestaurantDBUrl+"/deleteCuisine/"+restaurantId+"/"+cuisineName,requestOptions);
  }

  deleteFoodItem(restaurantId:string,cuisineName:string,foodItemName:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.delete(this.RestaurantDBUrl+"/deleteFoodItem/"+restaurantId+"/"+cuisineName+"/"+foodItemName,requestOptions);
  }


  deleteRestaurant(restaurantId:string)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.delete(this.RestaurantDBUrl+"/deleteRestaurant/"+restaurantId,requestOptions);
  }



  updateOrderStatus(order:Order)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.put(this.RestaurantDBUrl+"/updateOrderStatus",order,requestOptions);
  }

  getDistinctCityNames()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.RestaurantDBUrl+"/RestaurantByCity/",requestOptions);
  }



restaurantId?:any;

getRestaurantId()
{
    return this.restaurantId;
}
setRestaurantId(restId:any)
{
this.restaurantId=restId;
}

  tempCuisineName?:String
  getCuisineName()
  {
    return this.tempCuisineName;
  }
  setCuisineName(cuisineName:string)
  {
   this.tempCuisineName=cuisineName;
  }

  tempFoodItem?:String
  getFoodItemName()
  {
    return this.tempFoodItem;
  }
  setFooditemName(foodItemName:string)
  {
    this.tempFoodItem=foodItemName;
  }

  tempCity:any;
  getCityName()
  {
    return this.tempCity;
  }
  setCityName(cityName:any)
  {
    console.log(cityName);
    console.log(this.tempCity);
    this.tempCity=cityName;
    localStorage.setItem("cityName",this.tempCity)
    console.log(this.tempCity);

  }

  checkImageTarget?:string;
  setCheckImageTarget(target:string){
      this.checkImageTarget=target
  }
  getCheckImageTarget(){
      return this.checkImageTarget;
  }
}
