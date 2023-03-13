import { Order } from "../Order";
import { cuisines } from "./Cuisines";
import { SAddress } from "./SAddress";

export class Restaurant{
    restaurantId?:string;
    restaurantName?:string;
     restaurantImage?:any[];
  menu?:cuisines[];
     address?:SAddress;
     sellerEmail?:string;
    orders?:Order[]
}