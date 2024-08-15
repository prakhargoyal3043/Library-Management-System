import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { receiveUser } from '../models/recieveUser.model';
import { BookService } from '../services/book.service';
import { recieveBook } from '../models/recieveBook.model';
import { HttpClient } from '@angular/common/http';
import { sendRenteeLog } from '../models/sendRenteeLog.model';

@Component({
  selector: 'app-rent-book',
  templateUrl: './rent-book.component.html',
  styleUrls: ['./rent-book.component.css']
})
export class RentBookComponent implements OnInit {
  issueDate: Date = new Date();
  returnDate: Date = new Date();
  rentalAmount: number | undefined;
  bookId: number = 0;
  log: sendRenteeLog = {
    userId: 0,
    bookId: 0,
    issueDate: this.issueDate,
    dueDate: this.returnDate,
    totalAmount: 0,
    amountPaid: 0,
  };
  book!: recieveBook;
  userId: string | null = localStorage.getItem('username');
  user!: receiveUser;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private router: Router,
    private userService: UserService,
    private http: HttpClient
  ) {}

 
  ngOnInit(): void {
    if (this.userId) {
      this.userService.getUserByUsername(this.userId).subscribe(
        response => {
          this.user = response;
          this.log.userId = response.userId;
        },
        error => {
          console.error('Error occurred fetching user details', error);
        }
      );
    } else {
      console.error('User ID not found in localStorage');
    }

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.bookId = parseInt(idParam, 10);
      if (!isNaN(this.bookId)) {
        this.bookService.getBookById(this.bookId).subscribe(
          (response: recieveBook) => {
            this.book = response;
            this.log.bookId = response.bookId;
            this.calculateRentalAmount(); // Calculate rental amount after book details are fetched
          },
          (error: any) => {
            console.error('Error fetching book details:', error);
          }
        );
      } else {
        console.error('Invalid book ID:', idParam);
      }
    } else {
      console.error('Book ID parameter missing in route');
    }
  }



  calculateRentalAmount(): void {
    if (this.book && this.issueDate && this.returnDate) {
      const diffTime = Math.abs(this.returnDate.getTime() - this.issueDate.getTime());
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // Convert milliseconds to days
      this.rentalAmount = 0.01 * this.book.bookPrice * diffDays; // 1% of book price per day
      this.log.totalAmount = this.rentalAmount;
    } else {
      console.error('Book details or dates are not available for rental amount calculation');
    }
  }

  onIssueDateChange(event: any): void {
    this.issueDate = new Date(event.target.value);
    this.calculateRentalAmount();
  }

  onReturnDateChange(event: any): void {
    this.returnDate = new Date(event.target.value);
    this.calculateRentalAmount();
  }



  submitRentForm(): void {
    if (this.log.userId && this.log.bookId && this.rentalAmount !== undefined) {
      this.http.post('http://localhost:8083/log', this.log, { responseType: 'text' })
        .subscribe(
          response => {
            console.log('Rent book successful:', response);
            this.router.navigate(['/home']);
          },
          error => {
            console.error('Error registering:', error);
          }
        );
    } else {
      console.error('Incomplete data for rent form submission');
    }
  }
  
}
