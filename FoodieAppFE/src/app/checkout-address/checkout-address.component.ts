import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditAddressComponent } from '../edit-address/edit-address.component';
import { UserDBService } from '../services/user-db.service';
import { UAddress } from '../model/User/UAddress';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-checkout-address',
  templateUrl: './checkout-address.component.html',
  styleUrls: ['./checkout-address.component.css']
})
export class CheckoutAddressComponent {
  constructor(private userDbService: UserDBService, private dialog: MatDialog,private cartService:CartService) {

  }

  ngOnInit(): void {
    this.getUserDetails();

  }
  userDetails?: any;

  getUserDetails() {
    this.userDbService.getUserDetails().subscribe(
      response => {
        this.userDetails = response;
        console.log(this.userDetails);
      });
  }


  getAllAddress() {
    this.userDbService.getUserAllAddress().subscribe(
      response => {
        this.userDetails = response;
      }
    )
  }


  deleteAddress(orderPlace: string) {
    console.log(orderPlace)
    this.userDbService.deleteAddressFromUser(orderPlace).subscribe(
      response => {

        this.getUserDetails();
      }
    )
  }

  addAddress() {

    const dialogRef = this.dialog.open(EditAddressComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.getUserDetails();
    });
    this.ngOnInit();
  }
  matDailog:boolean=false;
  setAddress(address?:UAddress){

    this.cartService.setUserAddres(<UAddress>address)
  }
}
