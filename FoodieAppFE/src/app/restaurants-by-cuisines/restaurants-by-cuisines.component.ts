import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { cuisines } from '../model/Seller/Cuisines';
import { Restaurant } from '../model/Seller/Restaurant';
import { RestaurantDBService } from '../services/restaurant-db.service';
import { SellerDBService } from '../services/seller-db.service';

@Component({
  selector: 'app-restaurants-by-cuisines',
  templateUrl: './restaurants-by-cuisines.component.html',
  styleUrls: ['./restaurants-by-cuisines.component.css']
})
export class RestaurantsByCuisinesComponent {
  constructor(private sellerDbService:SellerDBService,private restaurantDbService:RestaurantDBService,private activatedRoute: ActivatedRoute,private location:Location)
  {

  }
  city?:string;
  ngOnInit(): void {




    if(window.location.pathname.startsWith('/userprofile'))
    {
      this.city=<string>localStorage.getItem("cityName")
   console.log(this.city);
   this.activatedRoute.paramMap.subscribe(data => {
    let id = data.get('id');

    // this.getAllRestaurantsByCuisine(id);
    this.restaurantDbService.getAllRestaurantsByCuisine(id,this.city).subscribe(data=>this.allRestaurants=data)
    });
    }
    else{
      this.city=<string>localStorage.getItem("cityName")
      this.activatedRoute.paramMap.subscribe(data => {
        let id = data.get('id');

        this.getAllRestaurantsByCuisine(id);
        });
    }




  }



  setRestId(restaurantId:any)
  {
  this.restaurantDbService.setRestaurantId(restaurantId)
  }


allRestaurants:any;
  getAllRestaurantsByCuisine(cuisineName:any)
  {

this.restaurantDbService.getAllRestaurantsByCuisine(cuisineName,<string>this.city).subscribe(
  response=>{
    this.allRestaurants=<Restaurant[]>response;
    console.log(this.allRestaurants)

  }
)
  }
}
