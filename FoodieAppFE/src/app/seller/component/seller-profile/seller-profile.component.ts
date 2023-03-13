import { Component } from '@angular/core';
import { Seller } from 'src/app/model/Seller/Seller';
import { SellerDBService } from 'src/app/services/seller-db.service';

@Component({
  selector: 'app-seller-profile',
  templateUrl: './seller-profile.component.html',
  styleUrls: ['./seller-profile.component.css']
})
export class SellerProfileComponent {

  constructor(private sellerDbService:SellerDBService)
  {}

  ngOnInit(): void {
  
this.SellerDetails();
   
  }


  sellerDetails?:Seller;

  SellerDetails()
  {
  this.sellerDbService.getSellerDetails().subscribe(
   response=> {
    this.sellerDetails=response;
        console.log(this.sellerDetails);
  });
  }

  editProfile()
  {
  }
}
