import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Restaurant } from '../model/Seller/Restaurant';
import { Seller } from '../model/Seller/Seller';

@Injectable({
  providedIn: 'root'
})
export class SellerDBService {

  constructor(private httpclient:HttpClient){}

  SellerDBUrl = "http://localhost:5555/sellerService";
  
  getSellerAllRestaurant()
  {
    // localStorage.setItem('Token',"eyJhbGciOiJIUzUxMiJ9.eyJzZWxsZXJfZW1haWwiOiJqYWlwcmFzc2F0aDEyMzQ1QGdtYWlsLmNvbSIsInNlbGxlcl9yb2xlIjoiUk9MRV9TRUxMRVIiLCJpYXQiOjE2NzcxMzY3MTl9.VnSIvv1jWGzMCv8xnOvSagmPmDWXz-0Hz-f8Hm1CSvvYwsYcpmPu2YNrBgZEP2nPb5pQab7Yt8clUfCS16DxgA")

      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.SellerDBUrl+"/getSellerAllRestaurant",requestOptions);
  }

  getSellerDetails()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.SellerDBUrl+"/getSellerDetails",requestOptions);
  }

  getAllSeller()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.get(this.SellerDBUrl+"/getAllSeller",requestOptions);
  }

  addSeller(seller:Seller)
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.post(this.SellerDBUrl+"/addSeller",seller,requestOptions);
  }

  deleteSeller()
  {
      let httpHeaders= new HttpHeaders
      ({
          'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
      });
      let requestOptions = {headers : httpHeaders}
      return this.httpclient.delete(this.SellerDBUrl+"/addSeller",requestOptions);
  }

  addRestaurantToSeller(restaurant:Restaurant)
  {
    // localStorage.setItem('Token',"eyJhbGciOiJIUzUxMiJ9.eyJzZWxsZXJfZW1haWwiOiJqYWlwcmFzc2F0aDEyMzQ1QGdtYWlsLmNvbSIsInNlbGxlcl9yb2xlIjoiUk9MRV9TRUxMRVIiLCJpYXQiOjE2NzcxMzY3MTl9.VnSIvv1jWGzMCv8xnOvSagmPmDWXz-0Hz-f8Hm1CSvvYwsYcpmPu2YNrBgZEP2nPb5pQab7Yt8clUfCS16DxgA")
    let httpHeaders= new HttpHeaders
    ({
        'Authorization' : 'Bearer ' +localStorage.getItem('seller_Token')
    });
    console.log(localStorage.getItem('Token'))
    let requestOptions = {headers : httpHeaders}
    return this.httpclient.post(this.SellerDBUrl+"/addRestaurantToSeller",restaurant,requestOptions);
  }


}
