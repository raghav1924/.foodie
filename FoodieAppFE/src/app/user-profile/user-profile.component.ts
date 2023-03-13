import { keyframes } from '@angular/animations';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { EditAddressComponent } from '../edit-address/edit-address.component';
import { Restaurant } from '../model/Seller/Restaurant';
import { UAddress } from '../model/User/UAddress';
import { User } from '../model/User/User';
import { wishLists } from '../model/User/wishList';
import { RestaurantDBService } from '../services/restaurant-db.service';
import { UserDBService } from '../services/user-db.service';
import { UploadImageComponent } from '../upload-image/upload-image.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {

  constructor(private userDbService: UserDBService, private dialog: MatDialog,private restaurantDb:RestaurantDBService, private snackBar: MatSnackBar) {

  }

  ngOnInit(): void {

    this.getUserDetails();
    this.getAllCuisineFromWishlist();
    this.getAllRestaurantFromWishlist();
  }


  userDetails?: any;
  city:any;
  getUserDetails() {
    this.userDbService.getUserDetails().subscribe(
      response => {
        this.userDetails = response;
        console.log(this.userDetails)
        this.city=this.userDetails.addresses[0].city;
        // localStorage.setItem("cityName",this.city)
        this.restaurantDb.setCityName(this.city)
      });
  }

  editProfile() {
    // this.userDbService.updateUserProfileImage().subscribe(
    //   response=>{
    //   }
    // )
  }


  getAllAddress() {
    this.userDbService.getUserAllAddress().subscribe(
      response => {
        this.userDetails = response;
      }
    )
  }


  deleteAddress(orderPlace: string) {
    console.log(orderPlace)
    this.userDbService.deleteAddressFromUser(orderPlace).subscribe(
      response => {

        this.getUserDetails();
      }
    )
  }

  addAddress() {

    const dialogRef = this.dialog.open(EditAddressComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.getUserDetails();
    });
  }

  userCuisine: any;
  getAllCuisineFromWishlist() {
    this.userDbService.getUserWishlistAllCuisines().subscribe(
      response => {
        this.userCuisine = response;
      }
    )
  }

  deleteCuisineFromWishlist(cuisineName:any) {
    this.userDbService.deleteCuisineFromUserWishlist(cuisineName).subscribe(
      response => {
        this.snackBar.open(cuisineName+" Delete Successfully", "close", {
          duration: 1000,

          });
        this.getAllCuisineFromWishlist();
      }
    )
  }

  userRest:any[]=[];
  userRestaurants?:any;
  getAllRestaurantFromWishlist() {
    this.userDbService.getUserWishListAllRestaurants().subscribe(
      response => {

        this.userRestaurants =Object.create(response)
        for(let restaurantId in this.userRestaurants)
        {
          this.userRest.push(({name:this.userRestaurants[restaurantId],id:restaurantId}));
        }
      }
    )

  }

  deleteRestaurantFromWishlist(restaurantName:any)
  {
    this.userDbService.deleteRestaurantFromUserWishlist(restaurantName).subscribe(
      response => {
        this.userRest=[]
        this.snackBar.open(restaurantName+" Delete Successfully", "close", {
          duration: 1000,
          });
        this.getAllRestaurantFromWishlist();
      }
    )
  }

  getRestaurantByCuisisne(cuisineName:any)
  {
    this.snackBar.open(cuisineName, "close", {
      duration: 1000,
      });
  this.restaurantDb.setCityName(this.city);
  this.restaurantDb.setCuisineName(cuisineName);
  }
  imageDialog(targrt:string) {

    this.restaurantDb.setCheckImageTarget(targrt)
    const dialogRef = this.dialog.open(UploadImageComponent);

    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {
        this.ngOnInit()
      }, 1000);
      // setTimeout(()=>this.checkLogin(),500)
      // this.getUserDetails();
    });
  }
}
