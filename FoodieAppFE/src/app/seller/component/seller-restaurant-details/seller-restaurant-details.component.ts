import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from 'src/app/model/Seller/Restaurant';
import { Seller } from 'src/app/model/Seller/Seller';
import { RestaurantDBService } from 'src/app/services/restaurant-db.service';
import { SellerDBService } from 'src/app/services/seller-db.service';
import { AddCuisineComponent } from '../add-cuisine/add-cuisine.component';
import { AddFoodItemComponent } from '../add-food-item/add-food-item.component';
import { UpdateFoodItemComponent } from '../update-food-item/update-food-item.component';
import { UploadImageComponent } from 'src/app/upload-image/upload-image.component';

@Component({
  selector: 'app-seller-restaurant-details',
  templateUrl: './seller-restaurant-details.component.html',
  styleUrls: ['./seller-restaurant-details.component.css']
})
export class SellerRestaurantDetailsComponent {

  constructor(private activatedRoute: ActivatedRoute,private restaurantDB:RestaurantDBService,private dialog:MatDialog,private snackBar:MatSnackBar,private route:Router) {
}
restaurantDeatils?:Restaurant;
ngOnInit(): void {

  this.activatedRoute.paramMap.subscribe(data => {
  let id = data.get('id');
  this.restaurantDB.setRestaurantId(id);
      this.restaurantDB.getRestaurantDetails(<string>id).subscribe(data => {

          this.restaurantDeatils = data;
      });
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
openDialogAddCuisine()
{
  const dialogRef = this.dialog.open(AddCuisineComponent);
  dialogRef.afterClosed().subscribe(result => {

    this.getRestaurantDetails(this.restaurantDeatils?.restaurantId);
  });
}
openDialog(cuisineName:any) {
  this.restaurantDB.setCuisineName(cuisineName);
  const dialogRef = this.dialog.open(AddFoodItemComponent);
  dialogRef.afterClosed().subscribe(result => {

    this.getRestaurantDetails(this.restaurantDeatils?.restaurantId);
  });
}

openDailogForUpadte(cuisineName:any,foodItemName:any){
  this.restaurantDB.setCuisineName(cuisineName);
  this.restaurantDB.setFooditemName(foodItemName);
  const dialogRef = this.dialog.open(UpdateFoodItemComponent);
  dialogRef.afterClosed().subscribe(result => {

    this.getRestaurantDetails(this.restaurantDeatils?.restaurantId);
  });
}

deleteCusine(restaurantId:any,cuisineName:any)
{
  this.restaurantDB.deleteCuisine(restaurantId,cuisineName).subscribe(
    response=>{
     this.getRestaurantDetails(restaurantId);
     this.snackBar.open(cuisineName+"  Deleted Successfully", "close", {
      duration: 2000,
      verticalPosition: 'top',

      });
    });
}

deleteFoodItem(restaurantId:any,cuisineName:any,foodItemName:any)
{
 this.restaurantDB.deleteFoodItem(<string>restaurantId,<string>cuisineName,<string>foodItemName).subscribe(
  response=>{
  this.getRestaurantDetails(restaurantId);
  this.snackBar.open(foodItemName+"  Deleted Successfully", "close", {
    duration: 2000,
    verticalPosition: 'top',

    });
  }
 )}

 deleteRestaurant(restaurantId:any)
 {
this.restaurantDB.deleteRestaurant(restaurantId).subscribe(response=>{
  console.log(response)
  this.route.navigateByUrl("seller/sellersRestaurant")
  this.snackBar.open(this.restaurantDeatils?.restaurantName+"  Deleted Successfully", "close", {
    duration: 2000,
    verticalPosition: 'top',

    });


 })
}

imageDialog(targrt:string) {

  this.restaurantDB.setCheckImageTarget(targrt)
  const dialogRef = this.dialog.open(UploadImageComponent);

  dialogRef.afterClosed().subscribe(result => {

    // setTimeout(()=>this.checkLogin(),500)
    // this.getUserDetails();
  });
}
foodImageDialog(targrt:string,restaurantId?:string,cuisineName?:string,foodItemName?:string) {
this.restaurantDB.setRestaurantId(restaurantId)
this.restaurantDB.setCuisineName(<string>cuisineName)
this.restaurantDB.setFooditemName(<string>foodItemName)
  this.restaurantDB.setCheckImageTarget(targrt)
  console.log(foodItemName)
  console.log(restaurantId)
  const dialogRef = this.dialog.open(UploadImageComponent);

  dialogRef.afterClosed().subscribe(result => {
    setTimeout(() => {
      this.ngOnInit()
      console.log("ngoninit close dialog")
    }, 2000);
  });
}
restaurantImageDialog(targrt:string,restaurantId?:string) {
this.restaurantDB.setRestaurantId(restaurantId)
  this.restaurantDB.setCheckImageTarget(targrt)
  console.log(restaurantId)

  const dialogRef = this.dialog.open(UploadImageComponent);

  dialogRef.afterClosed().subscribe(result => {
    // this.ngOnInit();
    setTimeout(() => {
      this.ngOnInit()
      console.log("ngoninit close dialog")
    }, 2000);
    // this.getRestaurantDetails(restaurantId)

  });
}


}
