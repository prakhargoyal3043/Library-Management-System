// import { Injectable } from '@angular/core';

// @Injectable({
//   providedIn: 'root'
// })
// export class UserService {

//   constructor() { }

// }
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { receiveUser } from '../models/recieveUser.model';
import { recieveRenteeLog } from '../models/recieveRenteeLog';

export interface RenteeLogDTO {
  bookId: string;
  noOfTimes: number;
  bookPrice: number;
  revenueGenerated: number;
  amountPaid:number;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8083/log/view'; // Replace with your actual API URL

  constructor(private http: HttpClient) { }

  private baseUrlById = 'http://localhost:8083/users/view'; // Endpoint to get book by id
  private baseUrlByName = 'http://localhost:8083/users/view/username'; // Endpoint to get book by userName
  private baseUrlRegister = ''
  
  getUserById(id: number): Observable<receiveUser> {
    return this.http.get<receiveUser>(`${this.baseUrlById}/${id}`);
  }
  getUserByUsername(userName: string): Observable<receiveUser> {
    return this.http.get<receiveUser>(`${this.baseUrlByName}/${userName}`);
  }
  getRenteeLogs(): Observable<RenteeLogDTO[]> {
    return this.http.get<RenteeLogDTO[]>(`${this.apiUrl}`);
  }
}
