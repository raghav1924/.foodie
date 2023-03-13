import { Injectable } from '@angular/core';
import { Order } from '../model/Order';
import { FoodItems } from '../model/FoodItems';
import { __values } from 'tslib';
import { Cart } from '../model/Cart';
import { UAddress } from '../model/User/UAddress';
import { CartItems } from '../model/CartItems';
import { RestaurantDBService } from './restaurant-db.service';
import { Restaurant } from '../model/Seller/Restaurant';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private restaurantDBService:RestaurantDBService,private snackBar:MatSnackBar) { }

  tempOrderDetails:Order={};
  tempCart:Cart=new Cart();

  setOrderDetails(){
    this.tempCart=this.getCartFromLocalStorage();

    // this.tempOrderDetails.address=uAddress;
    this.tempOrderDetails.orderStatus='ORDERED';
    this.tempOrderDetails.orderedOn= (new Date()).toString();
    this.tempOrderDetails.restaurantId=this.tempCart.restaurantId;
    this.tempOrderDetails.restaurantName=this.tempCart.restaurantName;
    this.tempOrderDetails.totalAmount=this.tempCart.totalAmount*0.85;
    this.tempOrderDetails.address=this.userAddress;
    this.tempOrderDetails.cartItems=this.tempCart.items;
    console.log("inside setOrderDetails")
    // for(let cartItem of this.tempCart.items){
    //   // console.log(cartItem)
    //   this.tempOrderDetails.foodItems?.set(cartItem.foodItems,cartItem.quantity);
    //   console.log(this.tempOrderDetails.foodItems)
    // }
    console.log(this.tempOrderDetails)
    console.log(typeof this.tempOrderDetails)
  }
  getOrderDetails(){
    return this.tempOrderDetails;
  }
  add2Cart(foodItem:FoodItems,restaurantId:string,restaurantName:string){
    // this.getResturantDetails(restaurantId);
    this.tempCart=this.getCartFromLocalStorage();

    if(this.tempCart.restaurantId==restaurantId || this.tempCart.restaurantId==null){
    let cartItem=this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName);
    console.log(cartItem)
    if(cartItem){
      (<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).quantity++;
      (<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).price=(<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).quantity*parseInt(<string>(<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).foodItem.itemPrice);
      this.tempCart.totalCount++;
      this.updateTotalAmount('ADD',foodItem);
    }
    else {
      this.tempCart.items.push(new CartItems(foodItem));
      this.tempCart.restaurantId=restaurantId;
      this.tempCart.restaurantName=restaurantName;
      this.tempCart.totalCount++;
      this.updateTotalAmount('ADD',foodItem);

    }
  }
  else{
    
    alert('You Can Order from One Restaurant at a time!!');
  }
    this.storeCartToLocalStorage();
  }
  removeFromCart(foodItem:FoodItems){
    this.tempCart=this.getCartFromLocalStorage();

    console.log(this.tempCart)
    let cartItem=this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName);
    if(cartItem){
      if((<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).quantity<=1){return;}
      (<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).quantity--;
      (<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).price=(<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).quantity*parseInt(<string>(<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).foodItem.itemPrice);
      this.tempCart.totalCount--;
      this.updateTotalAmount('REMOVE',foodItem);
   
    }

    else return;
    this.storeCartToLocalStorage();
  }

  storeCartToLocalStorage(){
    let currentCart=JSON.stringify(this.tempCart);
    localStorage.setItem('cart',currentCart);
    console.log(currentCart);
    console.log(this.tempCart);
  }

  getCartFromLocalStorage(){
    let currentCart=localStorage.getItem('cart');
    return currentCart?JSON.parse(currentCart):new Cart();
  }


clearCart(){
  this.tempCart=new Cart();
  this.storeCartToLocalStorage();
}



updateTotalAmount(type:string,foodItem:FoodItems){
  // this.tempCart=this.getCartFromLocalStorage();

  let cartItem=this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName);
  if(type=='REMOVE'){
    this.tempCart.totalAmount-= parseInt(<string>(<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).foodItem.itemPrice);

//  this.tempCart.totalAmount= this.tempCart.totalAmount*0.85

  }
  else{
    this.tempCart.totalAmount+= parseInt(<string>(<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).foodItem.itemPrice);
//  this.tempCart.totalAmount= this.tempCart.totalAmount*0.85

  }
}

deleteCartItem(foodItem:FoodItems){
  this.tempCart=this.getCartFromLocalStorage();

  console.log("inside Delete cart")
  console.log(this.tempCart)
  let tempIndex
  this.tempCart.items.forEach((item:CartItems,index)=>{
    console.log(index)
    console.log(item)
    if(item.foodItem.itemName==foodItem.itemName){
    this.tempCart.totalAmount-= ((<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).price);
   
    
    this.tempCart.totalCount-= (<CartItems>this.tempCart.items.find(item=>item.foodItem.itemName==foodItem.itemName)).quantity;
      this.tempCart.items.splice(index,1);

    }
    
  })
  if(this.tempCart.totalCount==0)
  {
    this.clearCart();
  }
  console.log(this.tempCart)
  this.storeCartToLocalStorage();
}
userAddress:UAddress={}
setUserAddres(userAddress:UAddress){
  this.userAddress=userAddress;
}

}
