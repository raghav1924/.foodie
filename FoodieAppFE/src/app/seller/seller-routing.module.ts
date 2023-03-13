import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Seller } from '../model/Seller/Seller';
import { SellerComponent } from './seller.component';
import { SellerRegisterComponent } from './component/seller-register/seller-register.component';
import { AddRestaurantComponent } from './component/add-restaurant/add-restaurant.component';
import { SellerDashboardComponent } from './component/seller-dashboard/seller-dashboard.component';
import { SellerRestaurantDetailsComponent } from './component/seller-restaurant-details/seller-restaurant-details.component';
import { SellerProfileComponent } from './component/seller-profile/seller-profile.component';
import { OrderRecieveComponent } from './component/order-recieve/order-recieve.component';
import { OrderDetailsComponent } from './component/order-details/order-details.component';

const routes: Routes = [
  {path:'seller',component:SellerComponent,
  children: [
    { path: 'sellerRegister', component: SellerRegisterComponent },
    {path:'addRestaurant',component:AddRestaurantComponent},
    {path:'sellersRestaurant',component:SellerDashboardComponent},
    {path:'sellerProfile',component:SellerProfileComponent},
    {path:'sellersRestaurant/:id',component:SellerRestaurantDetailsComponent},
    {path:'sellersRestaurant/:id/orderRecieve',component:OrderRecieveComponent},
    {path:'sellersRestaurant/:id/orderRecieve/:orderID',component:OrderDetailsComponent},
    // {path:'sellersRestaurant/:id/:#ids',component:SellerRestaurantDetailsComponent},
    {path: '', redirectTo:'sellerRegister',pathMatch:'full'},
  ]},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SellerRoutingModule { }
