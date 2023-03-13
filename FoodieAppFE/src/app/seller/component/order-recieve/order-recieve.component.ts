import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/model/Order';
import { Restaurant } from 'src/app/model/Seller/Restaurant';
import { Seller } from 'src/app/model/Seller/Seller';
import { RestaurantDBService } from 'src/app/services/restaurant-db.service';

@Component({
  selector: 'app-order-recieve',
  templateUrl: './order-recieve.component.html',
  styleUrls: ['./order-recieve.component.css']
})
export class OrderRecieveComponent {

  constructor(
    private restaurantDB:RestaurantDBService,
    private activatedRoute: ActivatedRoute
  ){}
  tempInterval?:any;
  ngOnDestroy() {
    clearInterval(this.tempInterval);
  }

  ngOnInit(): void
  {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.activatedRoute.paramMap.subscribe(data => {
      let id = data.get('id');
      // this.restaurantDB.setRestaurantId(id);
          // this.restaurantDB.getRestaurantDetails(<string>id).subscribe(data => {
          //   console.log("get restaurant details")
          //     this.restaurantDetails = data;
          // });
          console.log("this is restaurant ID from Path :  "+id);
          this.tempInterval= setInterval(()=> this.getRestaurantDetail(<string>id),2000)

      });
  }
  restaurantDetails:Restaurant={}
  getRestaurantDetail(id?:string){
    this.restaurantDB.getRestaurantDetails(<string>id).subscribe(data => {
        console.log("get restaurant details")
        console.log(data)
          this.restaurantDetails = data;
      });
  }

  accept(order:Order){
    order.orderStatus='PREPARING'
    this.restaurantDB.updateOrderStatus(order).subscribe(response=>{
      // this.getRestaurantDetail((<Order>response).restaurantId);
      this.ngOnInit();
    })
  }
  cancel(order:Order){
    order.orderStatus='CANCELLED'
    this.restaurantDB.updateOrderStatus(order).subscribe(response=>{
      // this.getRestaurantDetail((<Order>response).restaurantId);
      this.ngOnInit();
    })
  }
  outForDelivery(order:Order){
    order.orderStatus='OUTFORDELIVERY'
    this.restaurantDB.updateOrderStatus(order).subscribe(response=>{
      // this.getRestaurantDetail((<Order>response).restaurantId);
      this.ngOnInit();
    })
  }
  delivered(order:Order){
    order.orderStatus='DELIVERED'
    this.restaurantDB.updateOrderStatus(order).subscribe(response=>{
      // this.getRestaurantDetail((<Order>response).restaurantId);
      this.ngOnInit();
    })
  }
}
