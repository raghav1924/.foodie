import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { SellerSignUp } from 'src/app/model/Seller/SellerSignUp';
import { OTPCheckComponent } from 'src/app/otpcheck/otpcheck.component';
import { SellerAuthService } from 'src/app/services/seller-auth.service';

@Component({
  selector: 'app-seller-register',
  templateUrl: './seller-register.component.html',
  styleUrls: ['./seller-register.component.css']
})
export class SellerRegisterComponent {

  constructor(private help:SellerAuthService,public dialog: MatDialog){}


  phonePattern = '^[7-9][0-9]{9}$';
passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$";
emailpattern='^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'

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
  }
ngOnInit(): void {
  //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
  //Add 'implements OnInit' to the class.

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
    console.log(this.signupForm.value);
    this.help. sellerRegistration(<SellerSignUp>this.signupForm.value).subscribe({next:data=>{
      this.receivedData=data;
      console.log(this.receivedData);
    },error:e=>alert(`${e.message}\n${e.status}`)});

  }

  verifed:boolean=false;

  openOTPDailog() {
    console.log(this.signupForm.value.email)
    this.help.generateOTP(<string>this.signupForm.value.email).subscribe(response=>{
      console.log(response)
      this.help.setOTP(<string>response)

    })
    const dialogRef = this.dialog.open(OTPCheckComponent);

    dialogRef.afterClosed().subscribe(result => {
      if(this.help.getOTPVerified()==true){
        this.signupForm.get('name')?.enable();
        this.signupForm.get('phoneNo')?.enable();
        this.signupForm.get('password')?.enable();
        this.signupForm.get('confirmPassword')?.enable();

        let tickbtn:HTMLElement;
        tickbtn=<HTMLElement>document.getElementById('tickbtn');
        tickbtn.style.color='#5cb85c';
      }
      this.verifed=this.help.getOTPVerified();
    });
  }
}
