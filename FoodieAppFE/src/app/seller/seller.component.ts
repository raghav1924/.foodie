import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SellerLoginSignupComponent } from './component/seller-login-signup/seller-login-signup.component';
import { AddRestaurantComponent } from './component/add-restaurant/add-restaurant.component';
import { Seller } from '../model/Seller/Seller';
import { SellerDBService } from '../services/seller-db.service';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent {
  constructor(private router: Router,public dialog: MatDialog,private sellerDbService:SellerDBService) { }

  sellerDetails?:Seller;

  ngOnInit(): void {
    // this.router.navigate(['/sellerMain', { outlets: { seller: ['sellerRegister'] } }]);
this.getSellerDetails();
     this.checkLogin();
  }
  getSellerDetails(){
    this.sellerDbService.getSellerDetails().subscribe(
      response=> {
        this.sellerDetails=response;
        console.log(this.sellerDetails);
     });
  }

  openLoginDailog() {
    const dialogRef = this.dialog.open(SellerLoginSignupComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.checkLogin();
    });
  }
  openAddRestaurantDailog() {
    const dialogRef = this.dialog.open(AddRestaurantComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.checkLogin();
    });
  }
logedIN:boolean=false;
checkLogin(){
  if(localStorage.getItem("seller_Token")==''){
    this.logedIN=false;
  }else{
    this.getSellerDetails();
    this.logedIN=true;
  }
}

logOut(){
  localStorage.setItem("seller_Token",'');
  this.checkLogin();
  this.router.navigateByUrl("seller/sellerRegister")
}


}
