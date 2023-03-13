import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SellerLoginSignupComponent } from '../seller-login-signup/seller-login-signup.component';
import { AddRestaurantComponent } from '../add-restaurant/add-restaurant.component';

@Component({
  selector: 'app-seller-main',
  templateUrl: './seller-main.component.html',
  styleUrls: ['./seller-main.component.css']
})
export class SellerMainComponent {

  constructor(private router: Router,public dialog: MatDialog) { }

  ngOnInit(): void {
    this.router.navigate(['/sellerMain', { outlets: { seller: ['sellerRegister'] } }]);
  }

  openLoginDailog() {
    const dialogRef = this.dialog.open(SellerLoginSignupComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  openAddRestaurantDailog() {
    const dialogRef = this.dialog.open(AddRestaurantComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}

