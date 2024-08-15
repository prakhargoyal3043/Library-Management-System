import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { recieveBook } from '../models/recieveBook.model';
import { HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrlById = 'http://localhost:8083/books/find'; // Endpoint to get book by id
  private baseUrlAll = 'http://localhost:8083/books';  // Endpoint to get all books
  private baseUrlSearch = 'http://localhost:8083/books/search'; // Endpoint to search books
  private baseUrlUpdate = 'http://localhost:8083/books/update'; // Endpoint to update book
  private baseUrlDelete = 'http://localhost:8083/books/delete';

  constructor(private http: HttpClient) {}

  getBookById(id: number): Observable<recieveBook> {
    return this.http.get<recieveBook>(`${this.baseUrlById}/${id}`);
  }

  getBooks(): Observable<recieveBook[]> {
    return this.http.get<recieveBook[]>(`${this.baseUrlAll}`);
  }

  updateBook(book: recieveBook): Observable<recieveBook> {
    return this.http.put<recieveBook>(`${this.baseUrlUpdate}/${book.bookId}`, book);
  }

  searchBooks(query: string): Observable<recieveBook[]> {
    return this.http.get<recieveBook[]>(`${this.baseUrlSearch}?query=${query}`);
  }

  

  deleteBookById(id: number): Observable<string> {
    const url = `${this.baseUrlDelete}/${id}`; // Correct interpolation here
    return this.http.delete<string>(url, { responseType: 'text' as 'json' });
  }
  
}