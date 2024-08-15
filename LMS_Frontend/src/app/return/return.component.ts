import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { recieveBook } from '../models/recieveBook.model';
import { ReturnService } from '../services/return.service';
import { UserService } from '../services/user.service';
import { recieveRenteeLog } from '../models/recieveRenteeLog';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-return',
  templateUrl: './return.component.html',
  styleUrls: ['./return.component.css']
})
export class ReturnComponent implements OnInit {
  
  username: string | null = localStorage.getItem('username');
  userId!: number;
  bookIds: number[] = [];
  issuedBooks: recieveBook[] = [];
  updatedLog: recieveRenteeLog = {
    rentId: 0,
    userId: 0,
    bookId: 0,
    issueDate: new Date(),
    dueDate: new Date(),
    totalAmount: 0,
    amountPaid: 0,
    returnDate: new Date()
  };
  rentId!: number;

  constructor(
    private router: Router,
    private userService: UserService,
    private returnService: ReturnService,
    private bookService: BookService
  ) {}

  ngOnInit(): void {
    if (this.username) {
      this.userService.getUserByUsername(this.username).subscribe(
        response => {
          this.userId = response.userId;
          this.fetchBookIdsAndDetails();
        },
        error => {
          console.error('Error occurred fetching user details', error);
        }
      );
    } else {
      console.error('User ID not found in localStorage');
    }
  }

  fetchBookIdsAndDetails(): void {
    if (this.userId) {
      this.returnService.getBookIds().subscribe(
        (bookIdsMap: Map<number, number[]>) => {
          this.bookIds = bookIdsMap.get(this.userId) || [];
          console.log(this.bookIds);
          this.fetchBooksDetails(); // Call after book IDs are set
        },
        error => {
          console.error('Error fetching book IDs:', error);
        }
      );
    }
  }

  fetchBooksDetails(): void {
    if (this.bookIds.length > 0) {
      const issuedBooks: recieveBook[] = [];
  
      // Process each book ID individually
      this.bookIds.forEach(id => {
        this.bookService.getBookById(id).subscribe(
          (book: recieveBook) => {
            // Add the fetched book to the issuedBooks array
            issuedBooks.push(book);
            console.log(`Book fetched successfully:`, book);
          },
          (error: any) => {
            console.error(`Error fetching book with ID ${id}:`, error);
          },
          () => {
            // Update issuedBooks after all fetches complete
              this.issuedBooks = issuedBooks;
              console.log('Final issuedBooks:', this.issuedBooks);
          }
        );
      });
    } else {
      console.log('No book IDs available to fetch details.');
    }
  }
  
  
  
  

  returnBook(bookId: number): void {
    // Find the book in the issuedBooks array
    const book = this.issuedBooks.find(b => b.bookId === bookId);
  
    if (book) {
      console.log('Returning book:', book); // Debug: Log the book object
      console.log('Rent ID:', book.rentId); // Debug: Log the rent ID
  
      // Assign rentId and other properties for return
      this.updatedLog.rentId = book.rentId;
      this.updatedLog.returnDate = new Date();
  
      // Check if rentId is not null or undefined
      if (this.updatedLog.rentId != null) {
        // Call the return service with the rent ID and updated log
        this.returnService.returnBook(this.updatedLog.rentId, this.updatedLog).subscribe(
          response => {
            console.log('Book returned successfully:', response); // Debug: Log response
            alert('Book returned successfully!');
            
            this.fetchBookIdsAndDetails(); // Refresh the list after return
            this.router.navigate(['/home']);
          },
          error => {
            console.error('Error returning book:', error);
            alert('Error returning book!');
          }
        );
      } else {
        console.error('Rent ID is null or undefined.');
      }
    } else {
      console.error('Book not found in issuedBooks for bookId:', bookId);
    }
  }
  
}
