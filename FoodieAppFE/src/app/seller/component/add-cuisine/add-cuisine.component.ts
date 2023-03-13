import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RestaurantDBService } from 'src/app/services/restaurant-db.service';
import { Restaurant } from 'src/app/model/Seller/Restaurant';
import { cuisines } from 'src/app/model/Seller/Cuisines';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-cuisine',
  templateUrl: './add-cuisine.component.html',
  styleUrls: ['./add-cuisine.component.css']
})
export class AddCuisineComponent {

  constructor(private fb:FormBuilder,private restaurantDb:RestaurantDBService,private snackBar:MatSnackBar){
  }

  
  
    AddCuisines = this.fb.group({
    
      cuisineName:['',Validators.required]
    
    }); 

    restaurantDeatils?:Restaurant;
    sendToCuisineList()
    {
      
      this.restaurantDb.addCuisineToList(this.restaurantDb.getRestaurantId(),this.AddCuisines.value).subscribe(
        response=>{
          this.snackBar.open(this.AddCuisines.value.cuisineName+"  Added Successfully", "close", {
            duration: 1000,
        
            });
        }) ;
    }

}
