import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { recieveRenteeLog } from '../models/recieveRenteeLog';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { sendRenteeLog } from '../models/sendRenteeLog.model';

@Injectable({
  providedIn: 'root'
})
export class ReturnService {

  constructor(private http: HttpClient) { }

  private getBooksById = 'http://localhost:8083/log/view';
  private getBooks = 'http://localhost:8083/log/details';
  private getLogsbyBookId = 'http://localhost:8083/log/date';
  private returnBookUrl = 'http://localhost:8083/log/update'

  getUserById(id: number): Observable<recieveRenteeLog> {
    return this.http.get<recieveRenteeLog>(`${this.getBooksById}/${id}`);
  }

  getBookLogs(id: number): Observable<recieveRenteeLog> {
    return this.http.get<recieveRenteeLog>(`${this.getBooksById}/${id}`);
  }


  private getBooksByUserIdUrl = 'http://localhost:8083/log/view';



  getBookIds(): Observable<Map<number, number[]>> {
    return this.http.get(`${this.getBooks}`).pipe(
      map((response: Object) => {
        const responseData = response as { [key: string]: number[]; };
        const map = new Map<number, number[]>();
        Object.keys(responseData).forEach(key => {
          map.set(Number(key), responseData[key]);
        });
        return map;
      })
    );
  }





  // Method to update the return log of a book
  returnBook(rentId: number, log: sendRenteeLog): Observable<recieveRenteeLog> {
    return this.http.put<recieveRenteeLog>(`${this.returnBookUrl}/${rentId}`, log);
  }


}
