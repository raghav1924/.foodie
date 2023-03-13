import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Restaurant } from '../../../model/Seller/Restaurant';
import { RestaurantDBService } from '../../../services/restaurant-db.service';
import { SellerDBService } from '../../../services/seller-db.service';

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent {
  constructor(private fb:FormBuilder,private sellerService:SellerDBService,private snackBar:MatSnackBar){

  }
ngOnInit(): void
{  }

AddRestuarant = this.fb.group({
  restaurantName:['',Validators.required],
  address:this.fb.group({
    street: [''],
    city: [''],
    state: [''],
    zipcode: ['',[Validators.minLength(5)]]
  })
});

registerRestuarant(){
 
  this.sellerService.addRestaurantToSeller(<Restaurant>this.AddRestuarant.value).subscribe(
    response=>{
    window.location.reload();
     this.snackBar.open(this.AddRestuarant.value.restaurantName+"  Added Successfully", "close", {
      duration: 1000,

      });
    }) ;
}

}
