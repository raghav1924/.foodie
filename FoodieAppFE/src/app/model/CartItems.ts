import { FoodItems } from "./FoodItems";

export class CartItems{
  constructor(public foodItem:FoodItems){}
  quantity:number=1;
  price:number=parseInt(<string>this.foodItem.itemPrice);
}
