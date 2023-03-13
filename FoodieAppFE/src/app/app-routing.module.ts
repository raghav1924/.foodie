import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { UserLoginSignupComponent } from './user-login-signup/user-login-signup.component';
import { SellerLoginSignupComponent } from './seller/component/seller-login-signup/seller-login-signup.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CuisineCarouselComponent } from './cuisine-carousel/cuisine-carousel.component';
import { AddRestaurantComponent } from './seller/component/add-restaurant/add-restaurant.component';
import { SellerDashboardComponent } from './seller/component/seller-dashboard/seller-dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { WishListComponent } from './wish-list/wish-list.component';
import { CartComponent } from './cart/cart.component';
import { RestaurantsByCuisinesComponent } from './restaurants-by-cuisines/restaurants-by-cuisines.component';
import { CheckoutAddressComponent } from './checkout-address/checkout-address.component';
import { OrderTrackingComponent } from './order-tracking/order-tracking.component';
import { UploadImageComponent } from './upload-image/upload-image.component';

const routes: Routes = [
  {path:'home',component:HomePageComponent},
  {path:'userLogin',component:UserLoginSignupComponent},
  {path:'sellerLogin',component:SellerLoginSignupComponent},
  {path:'cuisine',component:CuisineCarouselComponent},
  {path:'home/:id',component:RestaurantDetailsComponent},
  {path:'home/cuisine/:id',component:RestaurantsByCuisinesComponent},
  {path:'userprofile/cuisine/:id',component:RestaurantsByCuisinesComponent},
  {path:'userprofile',component:UserProfileComponent},
  {path:'userprofile/:id',component:RestaurantDetailsComponent},
  {path:'wishlist',component:WishListComponent},
  {path:'cart',component:CartComponent},
  {path:'cart/:orderID',component:OrderTrackingComponent},
  {path:'checkoutAddress',component:CheckoutAddressComponent},
  {path:'uploadImage',component:UploadImageComponent},
  {path: '', redirectTo:'/home',pathMatch:'full'},
  // {path: '**', component:PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
