import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from './model/Seller/Restaurant';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FoodieAppFE';

  checkPath:boolean=false;

  constructor(private activatedRoute: Router,private dialog:MatDialog,public location:Location) {

  }


backbtn=true;
count:number=0;

  restaurantDeatils?:Restaurant;
  ngOnInit(): void
   {
    this.location.onUrlChange((url: string) => {

      if(url=='/home'){
        this.backbtn=false
      }else this.backbtn=true
      this.getCheckPath(url);

    });




  }


  getCheckPath(url:any){
    // console.log(url)
    if(url!='/home' && !url.startsWith('/seller')){
      this.checkPath=true
    }
    else{
      this.checkPath=false
    }
  }

}
