import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserSignUp } from '../model/User/UserSignUp';
import { UserAuthService } from '../services/user-auth.service';
import { OTPCheckComponent } from '../otpcheck/otpcheck.component';
import { User } from '../model/User/User';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-login-signup',
  templateUrl: './user-login-signup.component.html',
  styleUrls: ['./user-login-signup.component.css']
})
export class UserLoginSignupComponent {

  onClick(){
    document.querySelector('.cont')?.classList.toggle('s-signup');
  }

  constructor(private fb:FormBuilder,private userAuthService:UserAuthService,private router:Router,public dialog: MatDialog, private snackBar: MatSnackBar){}
  phonePattern = '^[7-9][0-9]{9}$';
passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$";
emailpattern='^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'

  loginForUser=this.fb.group({
    email:['',[Validators.required,Validators.pattern(this.emailpattern)]],
    password:['',[Validators.required,Validators.pattern(this.passwordPattern)]],
  });

responseData:any;
 sendLoginData()
 {
  console.log(this.loginForUser.value);
    this.userAuthService.userLogIn(<User>this.loginForUser.value).subscribe({next:response=>{
          this.responseData=response;
          localStorage.setItem("User_Token",this.responseData.token);
          localStorage.setItem("role",this.responseData.role);
          this.snackBar.open("WELCOME TO FOODIE APP", "close", {
            duration: 1000,
            });
          },error:e=> this.snackBar.open(`${"Password was Incorrect"}`,"close", {
            duration: 1000,
            })
       
      })
 }






  signupForm=new FormGroup({
    'email':new FormControl('',[Validators.required,Validators.pattern(this.emailpattern)]),
    'name':new FormControl('',[Validators.required]),
    'phoneNo':new FormControl('',[Validators.required,Validators.pattern(this.phonePattern)]),
    'password':new FormControl('',[Validators.required,Validators.pattern(this.passwordPattern)]),
    'confirmPassword':new FormControl('',[Validators.required,Validators.pattern(this.passwordPattern)]),
  }, { validators: [this.mustMatchValidator] });

  error_messages = {
    'name': [
      { type: 'required', message: 'Name is required.' },
    ],
    'email': [
      { type: 'required', message: 'Email is required.' },
    ],
    'phoneNo': [
      { type: 'required', message: 'Phone Number is required.' },
    ],
    'password': [
      { type: 'required', message: 'password is required.' },
    ],
    'confirmPassword': [
      { type: 'required', message: 'password is required.' },
      // { type: 'minlength', message: 'password length.' },
      // { type: 'maxlength', message: 'password length.' }
    ],
  };

  ngOnInit(): void {
    this.signupForm.get('name')?.disable();
    this.signupForm.get('phoneNo')?.disable();
    this.signupForm.get('password')?.disable();
    this.signupForm.get('confirmPassword')?.disable();
  }

  get name(){
    return this.signupForm.get('name');
  }
  get email(){
    return this.signupForm.get('email');
  }
  get phoneNo(){
    return this.signupForm.get('phoneNo');
  }
  get password(){
    return this.signupForm.get('password');
  }
  get confirmPassword(){
    return this.signupForm.get('confirmPassword');
  }

  mustMatchValidator(checkPassword: AbstractControl)
  {
    const passwordValue = checkPassword.get("password")?.value;
    const confirmPasswordValue = checkPassword.get("confirmPassword")?.value;
    if (!passwordValue || !confirmPasswordValue)
    {
      return null;
    }
    if (passwordValue != confirmPasswordValue)
    {
      return { mustMatch: false }
    }
    return null;
  }


  receivedData:any;

  sendSignUpData(){
   
    this.userAuthService.userRegistration(<UserSignUp>this.signupForm.value).subscribe({next:data=>{
      this.receivedData=data;
      this.snackBar.open("successfully Registered", "close", {
        duration: 1000,

        });
      document.querySelector('.cont')?.classList.toggle('s-signup');
    },error:e=>alert(`${e.message}\n${e.status}`)});

  }
  verifed:boolean=false;


  openOTPDailog() 
  {
    this.userAuthService.setTempForToCheckComponentIsCall('USEROTP');
    this.userAuthService.generateOTP(<string>this.signupForm.value.email).subscribe(response=>{
      this.userAuthService.setOTP(<string>response)

    })
    const dialogRef = this.dialog.open(OTPCheckComponent);

    dialogRef.afterClosed().subscribe(result => {
    this.userAuthService.setTempForToCheckComponentIsCall('');
      if(this.userAuthService.getOTPVerified()==true){
        this.signupForm.get('name')?.enable();
        this.signupForm.get('phoneNo')?.enable();
        this.signupForm.get('password')?.enable();
        this.signupForm.get('confirmPassword')?.enable();

        let tickbtn:HTMLElement;
        tickbtn=<HTMLElement>document.getElementById('tickbtn');
        tickbtn.style.color='#5cb85c';
      }
      this.verifed=this.userAuthService.getOTPVerified();
    });
  }
}
