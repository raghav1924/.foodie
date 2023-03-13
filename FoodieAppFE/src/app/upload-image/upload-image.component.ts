import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantDBService } from '../services/restaurant-db.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserDBService } from '../services/user-db.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent {

  url?:string;
  constructor(private activatedRoute: ActivatedRoute,private restaurantDB:RestaurantDBService,private dialog:MatDialog,private snackBar:MatSnackBar,private route:Router,private userDB:UserDBService) {}


//   onFileSelect(file:any)
//   {
//   if(file.target.files){
//     const reader=new FileReader();
//     reader.readAsDataURL(file.target.files[0]);
//     console.log(reader)
//     reader.onload=(event:any)=>{
//       this.url=event.target.result;
//     }
//   }
//   const filedata=file.target.files[0];
//   console.log(filedata);
//   console.log(file);
// }
ngOnInit(): void {
  //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
  //Add 'implements OnInit' to the class.
  console.log(this.url)
}

upload(){

  if(this.restaurantDB.getCheckImageTarget()=='RESTAURANT'){
    this.updateRestaurantImage(this.restaurantDB.getRestaurantId())
  }
  else if(this.restaurantDB.getCheckImageTarget()=='FOOD'){
    this.updateFoodItemImage(this.restaurantDB.getRestaurantId(),<string>this.restaurantDB.getCuisineName(),<string>this.restaurantDB.getFoodItemName())
  }
  else{
    this.updateProfileImage();
  }
}
updateRestaurantImage(restaurantId:string){
  const restaurantImageData= new FormData();
  console.log("inside onupload")
  restaurantImageData.append('restaurantImage',this.selectedFile,this.selectedFile?.name);
  console.log(restaurantImageData);
  this.restaurantDB.updateRestaurantImage(restaurantId,restaurantImageData).subscribe({next:data=>this.snack(data),error:e=>alert(`${e.message}\n${e.status}`)});
}
updateProfileImage(){
  const profileImageData= new FormData();
  console.log("inside onupload")
  profileImageData.append('profileImage',this.selectedFile,this.selectedFile?.name);
  console.log(profileImageData);
  this.userDB.updateUserProfileImage(profileImageData).subscribe({next:data=>this.snack(data),error:e=>alert(`${e.message}\n${e.status}`)});
}
snack(data:any){
  console.log(data)
  this.snackBar.open('Image Successfully added with ID: '+data, 'success', {​
    duration: 1000,​
    panelClass: ['mat-toolbar', 'mat-primary']​
    })

}

updateFoodItemImage(restaurantId:string,cuisineName:string,foodItemName:string){
  const foodItemImageData= new FormData();
  console.log("inside onupload")
  foodItemImageData.append('foodItemImage',this.selectedFile,this.selectedFile?.name);
  console.log(foodItemImageData);
  this.restaurantDB.updateFoodItemImage(restaurantId,cuisineName,foodItemName,foodItemImageData).subscribe({next:data=>this.snack(data),error:e=>alert(`${e.message}\n${e.status}`)});
}
selectedFile:any;
OnFileChange(file:any){
  this.selectedFile=file.target.files[0];
  if(file.target.files){
    const reader=new FileReader();
    reader.readAsDataURL(file.target.files[0]);
    console.log(reader)
    reader.onload=(event:any)=>{
      this.url=event.target.result;
    }
  }
}

}


