import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SellerRoutingModule } from './seller-routing.module';
import { SellerComponent } from './seller.component';
import { SellerDashboardComponent } from './component/seller-dashboard/seller-dashboard.component';
import { SellerLoginSignupComponent } from './component/seller-login-signup/seller-login-signup.component';
import { SellerMainComponent } from './component/seller-main/seller-main.component';
import { SellerProfileComponent } from './component/seller-profile/seller-profile.component';
import { AddRestaurantComponent } from './component/add-restaurant/add-restaurant.component';
import { SellerRegisterComponent } from './component/seller-register/seller-register.component';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SellerRestaurantDetailsComponent } from './component/seller-restaurant-details/seller-restaurant-details.component';
import { AddFoodItemComponent } from './component/add-food-item/add-food-item.component';
import { UpdateFoodItemComponent } from './component/update-food-item/update-food-item.component';
import { AddCuisineComponent } from './component/add-cuisine/add-cuisine.component';
import { OrderRecieveComponent } from './component/order-recieve/order-recieve.component';
import { OrderDetailsComponent } from './component/order-details/order-details.component';
import { AppComponent } from '../app.component';
import { FooterComponent } from './component/footer/footer.component';



@NgModule({
  declarations: [
    SellerComponent,SellerDashboardComponent,SellerLoginSignupComponent,SellerMainComponent,SellerProfileComponent,AddRestaurantComponent,SellerRegisterComponent, SellerRestaurantDetailsComponent, AddFoodItemComponent, UpdateFoodItemComponent, AddCuisineComponent, OrderRecieveComponent, OrderDetailsComponent, FooterComponent,
  ],
  imports: [
    CommonModule,
    SellerRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    FormsModule,ReactiveFormsModule,HttpClientModule,


  ]
})
export class SellerModule { }
