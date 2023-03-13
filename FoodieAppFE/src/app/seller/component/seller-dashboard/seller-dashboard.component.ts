import { Component } from '@angular/core';
import { Restaurant } from '../../../model/Seller/Restaurant';
import { RestaurantDBService } from '../../../services/restaurant-db.service';
import { SellerDBService } from '../../../services/seller-db.service';
import { Order } from 'src/app/model/Order';

@Component({
  selector: 'app-seller-dashboard',
  templateUrl: './seller-dashboard.component.html',
  styleUrls: ['./seller-dashboard.component.css']
})
export class SellerDashboardComponent {

  constructor(private sellerDbService:SellerDBService,private restaurantDbService:RestaurantDBService)
  {
  }
  tempInterval?:any;
  ngOnDestroy() {
    clearInterval(this.tempInterval);
  }
  ngOnInit(): void {

    this.tempInterval= setInterval(() => {
      this.RestaurantDetails();
    }, 10000);
    // this.currentDate=new Date();

    this.RestaurantDetails();
  }

  allRestaurants:Restaurant[]=[];
  RestaurantDetails()
  {
  this.sellerDbService.getSellerAllRestaurant().subscribe(
   response=> {
    this.restaurantData=<Map<string,string>>response
    this.RestaurantDetailsById(<Map<string,string>>response)
    // console.log("34")
    // console.log(this.restaurantData)
    // console.log("36")
  });
  }

  restaurantData:Map<string,string>=new Map();
  RestaurantDetailsById(restaurantData:Map<string,string>)
  {
  let restaurants= Object.create(this.restaurantData);
  // console.log(restaurants)
  this.allRestaurants=[];
   for(let restaurantId in restaurants)
   {
    // console.log(restaurants[restaurantId],restaurantId);
    this.restaurantDbService.getRestaurantDetails(restaurantId).subscribe(response=>{
            this.allRestaurants.push(response)
            // console.log(response)
              // console.log(this.allRestaurants);
            }

   )};
this.allRestaurants.sort((a:Restaurant,b:Restaurant)=>(<string>a.restaurantName)?.localeCompare(<string>b.restaurantName))
  }

  setRestId(restaurantId:any)
  {
  this.restaurantDbService.setRestaurantId(restaurantId)
  }
  currentDate:Date=new Date();

  notification(orders:Order[]):number{

    // let orders:Order[]=[]
    // setTimeout(() => {
    //   this.restaurantDbService.getRestaurantDetails(restaurantId).subscribe(response=>{
    //     orders=<Order[]>(<Restaurant>response).orders
    //   })
    // }, 1000);



    let notCount:number=0
    for(let order of orders){
      // console.log((new Date(<string>order.orderedOn)).getDate())

        if((new Date(<string>order.orderedOn)).getDate()==this.currentDate.getDate() && order.orderStatus=="ORDERED"){
          notCount++
        }
    }
    return notCount;
  }
}
