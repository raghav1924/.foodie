import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserDBService } from '../services/user-db.service';

@Component({
  selector: 'app-edit-address',
  templateUrl: './edit-address.component.html',
  styleUrls: ['./edit-address.component.css']
})
export class EditAddressComponent {
  constructor(private userDb:UserDBService,private snackBar:MatSnackBar)
  {
   
  }

  ngOnInit():void
  {
    this.userDb.getUserDetails().subscribe(response=>{
      this.userDetails=response;
    });  }

  addAddress=new FormGroup
  ({
    'orderPlace':new FormControl(),
    'doorNo':new FormControl(),
    'street':new FormControl(),
    'city':new FormControl(),
    'state':new FormControl(),
    'zipcode':new FormControl()
  
  });

  addAddresses()
  {
 
    this.userDb.addUserAddress(this.addAddress.value)
    .subscribe(response=>{
      this.snackBar.open("Address successfully Added", "close", {
        duration: 1000,
        });
    this.userDb.getUserDetails().subscribe(response=>{
      this.userDetails=response;
    });
   })
}

  
    userDetails?:any;

}
