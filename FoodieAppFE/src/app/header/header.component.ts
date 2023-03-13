import { Component } from '@angular/core';
import { Restaurant } from '../model/Seller/Restaurant';
import { UserLoginSignupComponent } from '../user-login-signup/user-login-signup.component';
import { RestaurantDBService } from '../services/restaurant-db.service';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../model/User/User';
import { UserDBService } from '../services/user-db.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

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
    console.log("tthis is testing"+option)
    this.restaurantDbService.setCityName(option)
    console.log("tthis is ooooo"+option)
    this.showDropdown=false;
    window.location.reload();
  }


allRestaurants?:Restaurant[]=[];

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
          // console.log("123456768kjbkds")
          setTimeout(()=>this.checkLogin(),500)
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
        // console.log("checklogin else")
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
      this.userDBService.getUserDetails().subscribe((response)=> {
          this.userDetails=response;
          // console.log(this.userDetails);
       });
    }
}
