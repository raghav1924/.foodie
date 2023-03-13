import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { RestaurantDBService } from 'src/app/services/restaurant-db.service';

@Component({
  selector: 'app-add-food-item',
  templateUrl: './add-food-item.component.html',
  styleUrls: ['./add-food-item.component.css']
})
export class AddFoodItemComponent {

  constructor(private fb:FormBuilder,private restaurantDb:RestaurantDBService,private router:Router,private snackBar:MatSnackBar){
  }
  
    AddFoodItems=this.fb.group({
      itemName:['',Validators.required],
      itemPrice:['',Validators.required],
      itemDescription:['',Validators.required]
  
    });
  addItems()
   {
    console.log(this.AddFoodItems.value)
    this.restaurantDb.addFoodItem(this.restaurantDb.getRestaurantId(),this.restaurantDb.getCuisineName(),this.AddFoodItems.value).subscribe(
      Response=>{
     this.snackBar.open(this.AddFoodItems.value.itemName+"  Added Successfully", "close", {
      duration: 1000,
  
      });
      }
    )};
}
