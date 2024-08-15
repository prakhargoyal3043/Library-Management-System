import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { recieveBook } from '../models/recieveBook.model';
import { BookService } from '../services/book.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-book',
  templateUrl: './show-book.component.html',
  styleUrls: ['./show-book.component.css']
})
export class ShowBookComponent implements OnInit {
  book: recieveBook | undefined;
  bookId: number | undefined;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.bookId = +idParam;
      this.fetchBookDetails(this.bookId);
    }
  }

  fetchBookDetails(id: number): void {
    this.bookService.getBookById(id).subscribe(
      (response: recieveBook) => {
        this.book = response;
      },
      (error: any) => {
        console.error('Error fetching book details:', error);
      }
    );
  }

  isUser(): boolean {
    const token = localStorage.getItem('token');
    return token === '898767934';
  }

  canEdit(): boolean {
    return !this.isUser(); // Inverted for demonstration, adjust as per your actual logic
  }

  canDelete(): boolean {
    return !this.isUser(); // Inverted for demonstration, adjust as per your actual logic
  }

  updateBook(): void {
    if (this.book) {
      this.bookService.updateBook(this.book).subscribe(
        (updatedBook: recieveBook) => {
          this.book = updatedBook;
          console.log('Book updated successfully:', updatedBook);
          // Optionally, navigate to a success page or show a confirmation
        },
        (error: any) => {
          console.error('Error updating book:', error);
          // Handle error scenario
        }
      );
    }
  }

  
  deleteBook(): void {
    if (this.bookId) {
      this.bookService.deleteBookById(this.bookId).subscribe(
        (response: string) => {
          console.log('Book deletion response:', response);
          this.router.navigate(['/home']).then(() => {
            alert('Book deleted successfully!');
          });
        },
        (error: any) => {
          console.error('Error deleting book:', error);
          // Handle error scenario
        }
      );
    }
  }
}
