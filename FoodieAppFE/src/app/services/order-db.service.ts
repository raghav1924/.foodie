import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Order } from '../model/Order';


@Injectable({
  providedIn: 'root'
})
export class OrderDBService {

  constructor(private httpclient:HttpClient){}

  OrderDBUrl = "http://localhost:5555/orderService";
  


  getOrderDetails(orderID:string)
  {
      let httpHeaders= new HttpHeaders
      ({
      'Authorization' : 'Bearer ' +localStorage.getItem('Token')
      });
      let requestOptions = {headers : httpHeaders}
      console.log(requestOptions);
      console.log(localStorage.getItem('Token'));
      return this.httpclient.get(this.OrderDBUrl+"/getOrderDetails/"+orderID,requestOptions);
  }
  

  addOrder(order:Order)
  {
    let httpHeaders=new HttpHeaders
    ({
      'Authorization' : 'Bearer ' +localStorage.getItem('Token')
    });
    let requestOptions = {headers : httpHeaders}
    return this.httpclient.post(this.OrderDBUrl+"/addOrder/",order,requestOptions);
  }

  updateOrderStatus(orderID:string,orderStatus:string)
  {
    let httpHeaders=new HttpHeaders
    ({
      'Authorization' : 'Bearer ' +localStorage.getItem('Token')
    });
    let requestOptions = {headers : httpHeaders}
    return this.httpclient.put(this.OrderDBUrl+"/updateOrderStatus/"+orderID+"/"+orderStatus,requestOptions);
  }
}
