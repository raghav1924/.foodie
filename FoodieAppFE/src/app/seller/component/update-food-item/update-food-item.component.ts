import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RestaurantDBService } from 'src/app/services/restaurant-db.service';

@Component({
  selector: 'app-update-food-item',
  templateUrl: './update-food-item.component.html',
  styleUrls: ['./update-food-item.component.css']
})
export class UpdateFoodItemComponent {
  constructor(private fb:FormBuilder,private restaurantDb:RestaurantDBService){}

  updateFood=this.fb.group({
    itemPrice:['',Validators.required],
    itemDescription:['',Validators.required]
  })

  updateFoodItem()
   {
   var food={
    "itemName":this.restaurantDb.getFoodItemName(),
    "itemPrice":this.updateFood.value.itemPrice,
    "itemDescription":this.updateFood.value.itemDescription
   }
   this.restaurantDb.updateFoodItem(this.restaurantDb.getRestaurantId(),<string>this.restaurantDb.getCuisineName(),food).subscribe(
     response=>{
      console.log(response);
     });
   }
}
