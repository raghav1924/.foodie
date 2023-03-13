import { Component } from '@angular/core';
import { SellerAuthService } from '../services/seller-auth.service';
import { UserAuthService } from '../services/user-auth.service';

@Component({
  selector: 'app-otpcheck',
  templateUrl: './otpcheck.component.html',
  styleUrls: ['./otpcheck.component.css']
})
export class OTPCheckComponent {

  firstDigit='';
  secondDigit='';
  thirdDigit='';
  fourthDigit='';

  constructor(private sellerAuthService:SellerAuthService,private userAuthService:UserAuthService){}
OTPVerified:boolean=false;
checkOTP(){
  let otp=this.firstDigit+this.secondDigit+this.thirdDigit+this.fourthDigit;
  console.log(otp)

  if(this.userAuthService.getTempForToCheckComponentIsCall()=='USEROTP'){
    if(this.userAuthService.getOTP()==otp){
      this.OTPVerified=true
      this.userAuthService.setOTPVerified(true)
    }else{
      alert("Incorrect OTP!!")
      this.OTPVerified=true
    }
  }
  else{
  if(this.sellerAuthService.getOTP()==otp){
    this.OTPVerified=true
    this.sellerAuthService.setOTPVerified(true)
  }else{
    alert("Incorrect OTP!!")
    this.OTPVerified=true
  }
}

}
}
