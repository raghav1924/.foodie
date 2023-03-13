import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantDBService } from '../services/restaurant-db.service';
import { MatDialog } from '@angular/material/dialog';
import { Restaurant } from '../model/Seller/Restaurant';
import { CartService } from '../services/cart.service';
import { FoodItems } from '../model/FoodItems';
import { Order } from '../model/Order';
import { UserDBService } from '../services/user-db.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserLoginSignupComponent } from '../user-login-signup/user-login-signup.component';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent {

  constructor(private activatedRoute: ActivatedRoute,private router:Router,private restaurantDB:RestaurantDBService,private dialog:MatDialog,private cart:CartService, private userDBService:UserDBService, private snackBar: MatSnackBar) {}


  restaurantDeatils?:Restaurant;
  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(data => {
    let id = data.get('id');
    this.restaurantDB.setRestaurantId(id);
    this.getRestaurantDetails(id);

    });
  }


  getRestaurantDetails(restaurantId:any)
  {
 
    this.restaurantDB.getRestaurantDetails(restaurantId).subscribe(
      response=>{
       this.restaurantDeatils=response;
      }
    )
  }

  checkItemQuantity(){
   
    return this.cart.getOrderDetails();
  }

  removeFromCart(){

  }
  quantity: number = 0;



  addToCart(foodItems:FoodItems,restaurantId?:string,restaurantName?:string)
 { 
  if(localStorage.getItem('User_Token')=="")
  {
    this.dialog.open(UserLoginSignupComponent);
  }
  else
   {
    this.cart.add2Cart(foodItems,<string>restaurantId,<string>restaurantName)
    this.snackBar.open(foodItems.itemName+"  Added To Cart", "close", {
      duration: 1000,
      });
  }
}




addtowishlist:any;
addRestToWishlist(restaurantName:any)
{
  if(localStorage.getItem('User_Token')!='')
  {
    this.userDBService.addRestaurantToUserWishlist(restaurantName,this.restaurantDB.getRestaurantId()).subscribe(
      response=>{
        this.addtowishlist=response;
        this.snackBar.open(restaurantName+" Restaurant Added to your wish list", "close", {
          duration: 1000,

          });
      }
    )
  }
  else{
    this.snackBar.open("You Have to Log-in", "close", {
      duration: 1000,
      });
  }

}

addCuisineTowishlist(cuisineName:any)
{
  if(localStorage.getItem('User_Token')!='')
  {
    this.userDBService.addCuisinesToUserWishlist(cuisineName).subscribe(
      response=>{
        this.addtowishlist=response;
        this.snackBar.open(cuisineName+"  Added To Your wishlist", "close", {
          duration: 1000,
          });
      }
    )
  }
  else{
    this.snackBar.open("You Have to Log-in", "close", {
      duration: 1000,
      });
  }
}

}
