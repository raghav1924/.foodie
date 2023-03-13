import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seller } from '../model/Seller/Seller';
import { SellerSignUp } from '../model/Seller/SellerSignUp';

@Injectable({
  providedIn: 'root'
})
export class SellerAuthService {

  constructor(private httpclient:HttpClient){}

  SellerAuthUrl = "http://localhost:5555/sellerAuth";

  generateOTP(email:string)
  {
      return this.httpclient.post(this.SellerAuthUrl+"/otp/",email);
  }


  sellerRegistration(sellerSignUp:SellerSignUp)
  {
      return this.httpclient.post(this.SellerAuthUrl+"/sellerRegistration/",sellerSignUp);
  }

  sellerLogIn(seller:any)
  {
      return this.httpclient.post(this.SellerAuthUrl+"/sellerLogIn",seller);
  }
  temmOTP=''
  setOTP(otp:string){
    this.temmOTP=otp
  }
  getOTP(){
    return this.temmOTP;
  }
OTPVerified=false
  setOTPVerified(verifed:boolean){
    this.OTPVerified=verifed
  }
  getOTPVerified(){
    return this.OTPVerified;
  }
}
 