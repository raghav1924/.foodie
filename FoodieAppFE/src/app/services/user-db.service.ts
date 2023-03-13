import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../model/Order';
import { UAddress } from '../model/User/UAddress';
import { User } from '../model/User/User';

@Injectable({
  providedIn: 'root'
})
export class UserDBService {

  constructor(private httpclient:HttpClient){
    let httpHeaders= new HttpHeaders
     ({
         'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
     });
     this.request = {headers : httpHeaders}
     console.log("this is about testing")
     console.log(this.request);
  }
request:any;
  ngOnInit() :void
 {
   let httpHeaders= new HttpHeaders
    ({
        'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
    });
    this.request = {headers : httpHeaders}
    console.log("this is about testing")
    console.log(this.request);
 }
  UserDBUrl = "http://localhost:5555/userService";

  getAllUser()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.UserDBUrl+"/getAllUser",requestOptions);
  }

  getUserDetails()
  {
    console.log("getUserDetails")
    // localStorage.setItem('Token',"eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX3JvbGUiOiJST0xFX1VTRVIiLCJ1c2VyX2VtYWlsIjoiamFpcHJhc3NhdGgxMjM0NUBnbWFpbC5jb20iLCJpYXQiOjE2Nzc0Njk1Mjh9.PdbrxUUPEavI_tVHXb0MXfKTRQ0bmwLPUOlF6R6KxB2hUNY5QR2lBL_fXXXhLDRtFC9zPI6GOoyklo7gi2RJ1g");

      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      this.request=requestOptions;
      return this.httpclient.get(this.UserDBUrl+"/getUserDetails",requestOptions);
  }

  getUserOrderDetails()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.UserDBUrl+"/getUserOrderDetails",requestOptions);
  }

  getUserWishlistAllCuisines()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.UserDBUrl+"/getUserWishlistAllCuisines",requestOptions);
  }

  getUserWishListAllRestaurants()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.UserDBUrl+"/getUserWishListAllRestaurants",requestOptions);
  }

  getUserAllAddress()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.UserDBUrl+"/getUserAllAddress",requestOptions);
  }

  addUser(user:User)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.post(this.UserDBUrl+"/addUser",user,requestOptions);
  }

  addUserAddress(uAddress:UAddress)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.post(this.UserDBUrl+"/addUserAddress",uAddress,requestOptions);
  }

  addOrderToUser(order:Order)
  {
      let httpHeaders= new HttpHeaders
      ({
        // 'Content-Type': 'application/json',
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      // console.log(requestOptions.headers)
      // console.log(JSON.stringify(order))
      return this.httpclient.post(this.UserDBUrl+"/addOrderToUser",order,requestOptions);
  }

  addCuisinesToUserWishlist(cuisineName:any)
  {

      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.post(this.UserDBUrl+"/addCuisinesToUserWishlist",cuisineName,requestOptions);
  }


  addRestaurantToUserWishlist(restaurantName:String,restaurantId:String)
  {
    console.log("this is about wishlist")
    let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      console.log("testingggg");
      console.log(this.request);
      return this.httpclient.post(this.UserDBUrl+"/addRestaurantToUserWishlist/"+restaurantId,restaurantName,this.request);
  }

  updateUserProfileImage(imageData:FormData)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}

      return this.httpclient.put(this.UserDBUrl+"/updateUserProfileImage",imageData,requestOptions);
  }

  updateUserAddress(orderPlace:String,address:UAddress)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.put(this.UserDBUrl+"/updateUserAddress/"+orderPlace,address,requestOptions);
  }

  updateOrderStatusToUser(orderId:String,OrderStatus:string,address:UAddress)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.put(this.UserDBUrl+"/updateOrderStatusToUser/"+orderId+"/"+OrderStatus,address,requestOptions);
  }

  deleteUser()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.delete(this.UserDBUrl+"/deleteUser",requestOptions);
  }

  deleteAddressFromUser(orderPlace:String)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.delete(this.UserDBUrl+"/deleteAddressFromUser/"+orderPlace,requestOptions);
  }

  deleteCuisineFromUserWishlist(cuisineName:String)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders,body:cuisineName}
      return this.httpclient.delete(this.UserDBUrl+"/deleteCuisineFromUserWishlist",requestOptions);
  }

  deleteRestaurantFromUserWishlist(restaurantName:String)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('User_Token')
      });
      let requestOptions = {headers : httpHeaders,body:restaurantName}
      return this.httpclient.delete(this.UserDBUrl+"/deleteRestaurantFromUserWishlist",requestOptions);
  }


}
