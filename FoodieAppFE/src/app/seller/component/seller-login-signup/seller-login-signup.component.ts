import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SellerAuthService } from '../../../services/seller-auth.service';

@Component({
  selector: 'app-seller-login-signup',
  templateUrl: './seller-login-signup.component.html',
  styleUrls: ['./seller-login-signup.component.css']
})
export class SellerLoginSignupComponent {

  constructor(private fb:FormBuilder,private sellerAuthService:SellerAuthService,private router:Router,private snackBar:MatSnackBar){}

  passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$";
emailpattern='^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'

  loginForSeller=this.fb.group({
    email:['',[Validators.required,Validators.pattern(this.emailpattern)]],
    password:['',[Validators.required]],
  });

responseData:any;
 sendLoginData()
 {
  console.log(this.loginForSeller.value);
    this.sellerAuthService.sellerLogIn(this.loginForSeller.value).subscribe({next:response=>{
          console.log(response);
          this.responseData=response;
          localStorage.setItem("seller_Token",this.responseData.token);
          localStorage.setItem("role",this.responseData.role);
          console.log(localStorage.getItem("seller_Token"));
            this.router.navigateByUrl("seller/sellersRestaurant")
            this.snackBar.open("welcome to Seller dashboard", "close", {
              duration: 1000,
        
              });
            },error:e=> this.snackBar.open(`${"Password was Incorrect"}`,"close", {
              duration: 1000,
              })
      })
 }

 error_messages = {
  'email': [
    { type: 'required', message: 'Email is required.' },
  ],
  'password': [
    { type: 'required', message: 'password is required.' },
  ],
}
}
