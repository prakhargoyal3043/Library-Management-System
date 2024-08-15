import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BookService } from '../services/book.service';
import { recieveBook } from '../models/recieveBook.model';
import { isFakeMousedownFromScreenReader } from '@angular/cdk/a11y';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
 
  searchResults: recieveBook[] = [];
  books: recieveBook[] = [];
  
  searchTerm: string = '';

  constructor(private router: Router, private httpClient: HttpClient, private bookService: BookService) {}

  ngOnInit(): void {
    this.fetchBooks();
  }

  fetchBooks(): void {
    // Adjust the API endpoint as per your backend
    const endpoint = 'http://localhost:8083/books';
    
    this.httpClient.get<recieveBook[]>(endpoint)
      .subscribe(
        (response: recieveBook[]) => {
          console.log('Books received:', response);
          this.books = response;
          this.filterBooks();
        },
        (error) => {
          console.error('Failed to fetch books:', error);
        }
      );
  }

  filterBooks(): void {
    if (this.searchTerm.trim() === '') {
      this.searchResults = [];
    } else {
      this.searchResults = this.books.filter(book =>
        book.bookName.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
  }


  clearSearchResults(): void {
    this.searchResults = [];
  }

  
  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  getRouteBasedOnToken(): string {
    const token = localStorage.getItem('token');

    if (token === '898767934') {
      return '/home';
    } else if (token === '765897953') {
      return '/librarian';
    } else {
      // Default route or handle other cases as needed
      return '/login'; // For example, redirect to login if token doesn't match expected values
    }
  }

  isUser() : boolean
  {
    const token = localStorage.getItem('token');
    if (token === '898767934') {
      return true;
    }
    return false;
  }
}
