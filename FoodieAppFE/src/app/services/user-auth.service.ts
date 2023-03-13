import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/User/User';
import { UserSignUp } from '../model/User/UserSignUp';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor(private httpclient:HttpClient){}

  userAuthUrl = "http://localhost:5555/userAuth";

  generateOTP(email:String)
  {
      return this.httpclient.post(this.userAuthUrl+"/otp",email);
  }

  userRegistration(userSignUp:UserSignUp)
  {
      return this.httpclient.post(this.userAuthUrl+"/userRegistration",userSignUp);
  }

  userLogIn(user:User)
  {
      return this.httpclient.post(this.userAuthUrl+"/userLogIn",user);
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

  tempForToCheckComponentIsCall='';
  setTempForToCheckComponentIsCall(set:any){
    this.tempForToCheckComponentIsCall=set
  }
  getTempForToCheckComponentIsCall(){
    return this.tempForToCheckComponentIsCall;
  }

}
