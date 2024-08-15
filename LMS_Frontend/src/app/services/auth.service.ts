
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HashService } from './hash.service';
import { sendUser } from '../models/sendUser.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


 
  private baseUrl: string = 'http://localhost:8083/users'; // Replace with your API base URL

  constructor(private http: HttpClient,private hash: HashService) { }


  login(userId: string, password: string): Observable<any> {
    const loginData = { userId, password };
    return this.http.post<string>(`${this.baseUrl}/login`, loginData)
      .pipe(
        map(token => {
          localStorage.setItem('token', token);
          return token;
        })
      );
  }

  

  register(userDetails: sendUser): Observable<any> {
    return this.http.post(`${this.baseUrl}`, userDetails);
  }

 
}