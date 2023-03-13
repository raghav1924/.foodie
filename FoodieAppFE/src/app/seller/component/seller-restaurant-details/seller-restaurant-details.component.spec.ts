import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerRestaurantDetailsComponent } from './seller-restaurant-details.component';

describe('SellerRestaurantDetailsComponent', () => {
  let component: SellerRestaurantDetailsComponent;
  let fixture: ComponentFixture<SellerRestaurantDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellerRestaurantDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SellerRestaurantDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
