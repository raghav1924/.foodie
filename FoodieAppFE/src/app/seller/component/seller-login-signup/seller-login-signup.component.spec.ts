import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerLoginSignupComponent } from './seller-login-signup.component';

describe('SellerLoginSignupComponent', () => {
  let component: SellerLoginSignupComponent;
  let fixture: ComponentFixture<SellerLoginSignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellerLoginSignupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SellerLoginSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
