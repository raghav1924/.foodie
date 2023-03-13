import { CartItems } from "./CartItems";
import { FoodItems } from "./FoodItems";
import { UAddress } from "./User/UAddress";

export class Order{
    orderId?:string;
    userId?:string;
    restaurantId?:string;
    restaurantName?:string;
    orderedOn?:string;
    orderStatus?:string;
    cartItems?:CartItems[];
    totalAmount?:number;
    address?:UAddress; 
}

