import { Component, OnInit } from '@angular/core';
import { recieveBook } from '../models/recieveBook.model';
import { HttpClient } from '@angular/common/http';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-librarian',
  templateUrl: './librarian.component.html',
  styleUrls: ['./librarian.component.css']
})
export class LibrarianComponent implements OnInit {
  constructor(private httpClient: HttpClient, private bookService: BookService) {}

  books: recieveBook[] = [];
  categories: string[] = [];
  authors: any[] = []; // Assuming authors will be fetched similarly

  ngOnInit(): void {
    this.httpClient.get<recieveBook[]>("http://localhost:8083/books").subscribe(
      response => {
        console.log(response);
        this.books = response;
      },
      error => {
        console.error('Getting all books failed', error);
      }
    );

    this.httpClient.get<string[]>("http://localhost:8083/books/categories").subscribe(
      response => {
        console.log(response);
        this.categories = response;
      },
      error => {
        console.error('Getting categories failed', error);
      }
    );

    // Assuming you fetch authors in a similar manner from your API
    this.httpClient.get<any[]>("http://localhost:8083/authors").subscribe(
      response => {
        console.log(response);
        this.authors = response;
      },
      error => {
        console.error('Getting authors failed', error);
      }
    );
  }
}
