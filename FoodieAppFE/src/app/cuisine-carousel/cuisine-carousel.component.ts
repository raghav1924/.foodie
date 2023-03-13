import { isDataSource } from '@angular/cdk/collections';
import { Component, ComponentFactoryResolver } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { flatMap } from 'rxjs';
import { Restaurant } from '../model/Seller/Restaurant';
import { RestaurantDBService } from '../services/restaurant-db.service';

@Component({
  selector: 'app-cuisine-carousel',
  templateUrl: './cuisine-carousel.component.html',
  styleUrls: ['./cuisine-carousel.component.css']
})
export class CuisineCarouselComponent {

  constructor(private restaurantDB:RestaurantDBService){}


   carousel:HTMLElement | null=null;
   isDragStart=false;
  prevPageX:number | undefined =0;
  prevScrollLeft:number | undefined=0;

  arrowIcon:NodeList | null=null;
  firstImg:HTMLElement | null=null;
  firstImgWidth:number | undefined;
  scrollWidth:number | undefined;

   ngOnInit() {
    this.carousel = document.querySelector('.carousel');
    this.isDragStart= false,this.prevPageX,this.prevScrollLeft;
    this.arrowIcon=document.querySelectorAll('.wrapper mat-icon');
    this.firstImg=document.querySelectorAll('img')[0];
    console.log(this.firstImg.clientWidth)
    this.firstImgWidth=this.firstImg.clientWidth+(0.01*(<HTMLElement>this.carousel).clientWidth);
    console.log(this.firstImgWidth)
    this.showHideIcons();
    this.scrollWidth=<number>this.carousel?.scrollWidth-<number>this.carousel?.clientWidth;

  }

showHideIcons(){
  console.log((<HTMLElement>this.arrowIcon![0]).style.display);
  console.log(this.carousel!.scrollLeft );
  console.log(this.scrollWidth);
  (<HTMLElement>this.arrowIcon![0]).style.display= this.carousel!.scrollLeft < 10 ? "none":"block";
  (<HTMLElement>this.arrowIcon![1]).style.display= this.carousel!.scrollLeft > <number>this.scrollWidth-2 ? "none":"block";
  console.log((<HTMLElement>this.arrowIcon![0]).style.display);

}
  onArrowClick(e:MouseEvent){
    console.log(this.firstImgWidth)

    this.carousel!.scrollLeft += <number>((<HTMLElement>e.target).id == "leftArrow"? -<number>this.firstImgWidth:this.firstImgWidth);
   setTimeout(()=>this.showHideIcons(),60);
    console.log(this.firstImgWidth)
    console.log(" onclick scollLft "+this.carousel?.scrollLeft)

      console.log((<HTMLElement>e.target).id);
  }


   dragging(e:MouseEvent | TouchEvent){
     console.log(this.isDragStart)
     if(!this.isDragStart) return;

     e.preventDefault();
     this.carousel?.classList.add('dragging')
     let positionDiff=((<MouseEvent>e).pageX || (<TouchEvent>e).touches[0].pageX)-<number>this.prevPageX;
     console.log("currentX "+(<MouseEvent>e).pageX);
    console.log("prevPageX "+this.prevPageX)
    console.log("positionDiff "+positionDiff);
    console.log("prevScrollLeft "+this.prevScrollLeft)
    console.log(" before scollLft "+this.carousel?.scrollLeft)

      this.carousel!.scrollLeft=<number>this.prevScrollLeft-positionDiff;
      this.showHideIcons();
      console.log(" after scollLft "+this.carousel?.scrollLeft)

   }

   dragStart(e:MouseEvent){
    console.log("before dragStart prevScrollLeft "+this.prevScrollLeft)
    console.log("before dragStart prevPageX "+this.prevPageX)
    this.isDragStart=true;
    this.prevPageX=e.pageX || (<TouchEvent><unknown>e).touches[0].pageX;
    this.prevScrollLeft=this.carousel?.scrollLeft;
    console.log("AFter dragStart prevScrollLeft "+this.prevScrollLeft)
    console.log("After dragStart prevPageX "+this.prevPageX)

   }

   dragStop(){
     this.isDragStart=false;
     this.carousel?.classList.remove('dragging')
   }
}
