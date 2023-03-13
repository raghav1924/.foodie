import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/model/Order';
import { OrderDBService } from 'src/app/services/order-db.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent {

  constructor(
    private orderDB:OrderDBService,
    private activatedRoute: ActivatedRoute
  ){}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(data => {
      let orderId = data.get('orderID');
          console.log("this is Order ID from Path :  "+orderId);
          this.getRestaurantDetail(<string>orderId);
      });
  }
  orderDetials:Order={}
  getRestaurantDetail(id?:string){
    this.orderDB.getOrderDetails(<string>id).subscribe(data => {
        console.log("get Order details")
        console.log(data)
          this.orderDetials = data;
      });
  }
}
