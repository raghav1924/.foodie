import { Component } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Cart } from '../model/Cart';
import { CartItems } from '../model/CartItems';
import { FoodItems } from '../model/FoodItems';
import { CheckoutAddressComponent } from '../checkout-address/checkout-address.component';
import { MatDialog } from '@angular/material/dialog';
import { UserDBService } from '../services/user-db.service';
import { Router } from '@angular/router';
import { Order } from '../model/Order';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {

  constructor(private cartService:CartService,
    private dialog:MatDialog,
    private UserDB:UserDBService,
    private router:Router
    ){}

  cart?:Cart;
  cartItem:CartItems[]=[];
  ngOnInit(): void {
    this.cart= this.cartService.getCartFromLocalStorage();
    this.cartItem=<CartItems[]>this.cart?.items;
    this.grandTotal=<number>this.cart?.totalAmount*0.85;
    console.log("inside ngOnInit")
    console.log(this.cart)
    console.log(this.cartItem)
  }
  decrease(foodItem:FoodItems) {
    this.cartService.removeFromCart(foodItem)
    console.log("decrease")
    this.ngOnInit();
  }

  increase(foodItem:FoodItems,restaurantId?:string,restaurantName?:string) {
    this.cartService.add2Cart(foodItem,<string>restaurantId,<string>restaurantName)
    this.ngOnInit();
  }
  clearCart(){
    this.cartService.clearCart();
    this.ngOnInit();
  }
  deleteCartItem(foodItem:FoodItems){
    this.cartService.deleteCartItem(foodItem);
    this.ngOnInit();
  }
  placeOrder(){

    const dialogRef = this.dialog.open(CheckoutAddressComponent);

    dialogRef.afterClosed().subscribe(result => {

      this.cartService.setOrderDetails();
      this.addOrderToUser();

    });
  }
  addOrderToUser(){
    this.UserDB.addOrderToUser(this.cartService.getOrderDetails()).subscribe(response=>{
      console.log('addOrderResponse!!!!!!!!!!')
      console.log(response)
    this.cartService.clearCart();
      this.router.navigateByUrl("cart/"+(<Order>response).orderId)
    }
    )
  }
  grandTotal:number=0;


}
