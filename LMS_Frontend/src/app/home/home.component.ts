import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BookService } from '../services/book.service';
import { Router } from '@angular/router';
import { recieveBook } from '../models/recieveBook.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  books: recieveBook[] = [];
  categories: string[] = [];
  filteredBooks: recieveBook[] = [];
  selectedCategory: string | null = null;

  constructor(private httpClient: HttpClient, private bookService: BookService, private router: Router) {}

  ngOnInit(): void {
    this.httpClient.get<recieveBook[]>("http://localhost:8083/books").subscribe(
      response => {
        this.books = response;
        this.filteredBooks = response; // Initialize filteredBooks with all books
      },
      error => {
        console.error('Getting all books failed', error);
      }
    );

    this.httpClient.get<string[]>("http://localhost:8083/books/categories").subscribe(
      response => {
        this.categories = response;
      },
      error => {
        console.error('Getting categories failed', error);
      }
    );
  }

  filterBooksByCategory(category: string | null): void {
    if (this.selectedCategory === category) {
      // If the same category is clicked again, deselect it
      this.selectedCategory = null;
      this.filteredBooks = this.books; // Show all books
    } else {
      // Otherwise, select the new category
      this.selectedCategory = category;
      this.filteredBooks = this.books.filter(book => book.bookCategory === category);
    }
  }

  navigateToBook(id: number): void {
    this.router.navigate(['/show-book', id]);
  }
}
