import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFoodItemComponent } from './add-food-item.component';

describe('AddFoodItemComponent', () => {
  let component: AddFoodItemComponent;
  let fixture: ComponentFixture<AddFoodItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFoodItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddFoodItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
