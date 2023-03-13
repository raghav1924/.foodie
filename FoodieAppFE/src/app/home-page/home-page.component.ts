import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserLoginSignupComponent } from '../user-login-signup/user-login-signup.component';
import { Restaurant } from '../model/Seller/Restaurant';
import { RestaurantDBService } from '../services/restaurant-db.service';
import { Route, Router } from '@angular/router';
import { UserAuthService } from '../services/user-auth.service';
import { UserDBService } from '../services/user-db.service';
import { User } from '../model/User/User';
import { wishLists } from '../model/User/wishList';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  logedIn=false;
  options:any[]=[];
  filteredOptions = this.options;
  selectedValue = '';
  selectedOption = '';
  showDropdown=false;

  filterOptions() {

    if(this.selectedValue=='' || this.filteredOptions.length==0) this.showDropdown=false;
    else this.showDropdown=true;
    this.filteredOptions = this.options.filter(option => option.toLowerCase().includes(this.selectedValue.toLowerCase()));
    if(this.filteredOptions.length==0) this.showDropdown=false;

  }

  selectOption(option: string) {
    this.selectedOption = option;
    this.selectedValue = option;
    this.showDropdown=false;
    this.getAllRestaurantsByCity();
  }

ngOnInit() {
this.getAllRestaurant();

this.checkLogin();
this.restaurantDbService.getDistinctCityNames().subscribe(
  response=>{
    this.options=<any[]>response
    })
}

getAllRestaurantsByCity()
{console.log(this.selectedValue)
  this.restaurantDbService.getAllRestaurantsByCity(this.selectedValue).subscribe(
    response=>{
      this.allRestaurants=<Restaurant[]>response;
      console.log(response)
    }
  )
}



allRestaurants?:Restaurant[]=[];
getAllRestaurant()
  {
  this.restaurantDbService.getAllRestaurant().subscribe(
   response=> {
    this.allRestaurants=<Restaurant[]>response;
  });
  }

  constructor(public dialog: MatDialog,private restaurantDbService:RestaurantDBService,private router:Router,private userDBService:UserDBService) {}

  openDialog() {
    const dialogRef = this.dialog.open(UserLoginSignupComponent);

    dialogRef.afterClosed().subscribe(result => {

      setTimeout(() => {this.ngOnInit()}, 1000);
      // this.getUserDetails();
    });
  }

  setRestId(restaurantId:any)
  {
  this.restaurantDbService.setRestaurantId(restaurantId)
  }
  logedIN:boolean=false;
checkLogin(){
  if(localStorage.getItem("User_Token")==''){
    this.logedIN=false;
  }else{
    console.log("checklogin else")
    this.getUserDetails();
    this.logedIN=true;
  }
}

logOut(){
  localStorage.setItem("User_Token",'');
  this.checkLogin();
  this.router.navigateByUrl("/home")
}

userDetails?:User;
getUserDetails(){
  console.log("kjjvhj")
  this.userDBService.getUserDetails().subscribe(
    response=> {
      this.userDetails=response;
      console.log(this.userDetails);
   });
}

}
