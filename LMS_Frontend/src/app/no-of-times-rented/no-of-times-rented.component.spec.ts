import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoOfTimesRentedComponent } from './no-of-times-rented.component';

describe('NoOfTimesRentedComponent', () => {
  let component: NoOfTimesRentedComponent;
  let fixture: ComponentFixture<NoOfTimesRentedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NoOfTimesRentedComponent]
    });
    fixture = TestBed.createComponent(NoOfTimesRentedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
