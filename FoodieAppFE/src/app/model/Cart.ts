import { CartItems } from "./CartItems";

export class Cart{
  items:CartItems[]=[];
  totalAmount:number=0;
  totalCount:number=0;
  restaurantId?:string;
  restaurantName?:string;
  
}
