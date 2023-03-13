import { Order } from "../Order";
import { UAddress } from "./UAddress";
import { wishLists } from "./wishList";

export class User
{
 email?:string;
  name?:string;
  phoneNo?:string;

  profileImage?:any[];

  address?:UAddress[];
 orders?:Order[];

 wishLists?:wishLists;
}
