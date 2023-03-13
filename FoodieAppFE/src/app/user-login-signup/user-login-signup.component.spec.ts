import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLoginSignupComponent } from './user-login-signup.component';

describe('UserLoginSignupComponent', () => {
  let component: UserLoginSignupComponent;
  let fixture: ComponentFixture<UserLoginSignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserLoginSignupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserLoginSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
