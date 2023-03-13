import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { UserLoginSignupComponent } from './user-login-signup/user-login-signup.component';
import { CartComponent } from './cart/cart.component';
import { WishListComponent } from './wish-list/wish-list.component';
import { PaymentComponent } from './payment/payment.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { CuisineCarouselComponent } from './cuisine-carousel/cuisine-carousel.component';
import { HttpClientModule } from '@angular/common/http';
import { OTPCheckComponent } from './otpcheck/otpcheck.component';
import { SellerModule } from './seller/seller.module';
import { EditAddressComponent } from './edit-address/edit-address.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { RestaurantsByCuisinesComponent } from './restaurants-by-cuisines/restaurants-by-cuisines.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { CheckoutAddressComponent } from './checkout-address/checkout-address.component';
import { OrderTrackingComponent } from './order-tracking/order-tracking.component';
import { UploadImageComponent } from './upload-image/upload-image.component';















@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    RestaurantDetailsComponent,
    PageNotFoundComponent,
    AdminPageComponent,
    UserLoginSignupComponent,
    CartComponent,
    WishListComponent,
    PaymentComponent,
    UserProfileComponent,
    UserDashboardComponent,
    CuisineCarouselComponent,
    OTPCheckComponent,
    EditAddressComponent,
    FooterComponent,
    HeaderComponent,
    RestaurantsByCuisinesComponent,
    CheckoutAddressComponent,
    OrderTrackingComponent,
    UploadImageComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    FormsModule,ReactiveFormsModule,HttpClientModule, SellerModule,MatSnackBarModule
  ],
  providers: [],
  exports: [MatIconModule, MatButtonModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
