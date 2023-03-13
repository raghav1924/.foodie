import { Component } from '@angular/core';
import { OrderDBService } from '../services/order-db.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from '../model/Order';
import { UserDBService } from '../services/user-db.service';
import { User } from '../model/User/User';
import { interval } from 'rxjs';

@Component({
  selector: 'app-order-tracking',
  templateUrl: './order-tracking.component.html',
  styleUrls: ['./order-tracking.component.css']
})
export class OrderTrackingComponent {

  constructor(
    private orderDB:OrderDBService,
    private activatedRoute: ActivatedRoute,
    private userDB:UserDBService,
    private router:Router
  ){}

  tempInterval?:any;
  ngOnDestroy() {
    clearInterval(this.tempInterval);
  }
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(data => {
      let orderId = data.get('orderID');
          console.log("this is Order ID from Path :  "+orderId);
          this.tempInterval=setInterval(()=>this.getUserDetail(<string>orderId),1000)
          this.getUserDetail(<string>orderId);
      });


  }



  orderDetials:Order={}
  userDetails:User={}
  getUserDetail(id?:string){
    console.log("dsfgdfhdfhshd")
    this.userDB.getUserDetails().subscribe(data => {
        console.log("get Order details")
        console.log(data)
          this.userDetails = data;
          this.checkForOrder(this.userDetails,<string>id);
      });

  }
checkForOrder(user:User,orderId:string){
  console.log("check For Order")
  user.orders?.forEach(order=>{
    if(order.orderId==orderId){
      this.orderDetials=order

    }
  })


  console.log(this.orderDetials)
  this.orderStatus=<string>this.orderDetials.orderStatus;
  if( this.orderStatus=='DELIVERED') {
    setTimeout(() => {
      this.router.navigate(['home'])
    }, 5000);
  }
  }
  orderStatus:string='ORDERED';


}

