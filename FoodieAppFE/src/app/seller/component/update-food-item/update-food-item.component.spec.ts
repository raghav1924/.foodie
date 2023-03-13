import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateFoodItemComponent } from './update-food-item.component';

describe('UpdateFoodItemComponent', () => {
  let component: UpdateFoodItemComponent;
  let fixture: ComponentFixture<UpdateFoodItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateFoodItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateFoodItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
