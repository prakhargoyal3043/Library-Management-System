import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, Input } from '@angular/core';
// import { MatDialog } from '@angular/material/dialog';
// import { RentComponent } from '../rent/rent.component'; // Adjust path based on your project structure
import { Book } from '../models/sendBook.model'; // Adjust path based on your project structure

import { ShowBookComponent } from './show-book.component';

describe('ShowBookComponent', () => {
  let component: ShowBookComponent;
  let fixture: ComponentFixture<ShowBookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowBookComponent]
    });
    fixture = TestBed.createComponent(ShowBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
