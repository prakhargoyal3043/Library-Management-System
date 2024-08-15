import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { receiveUser } from '../models/recieveUser.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})



  export class UserComponent {
    users: any[] = []; // Change 'any' to the actual type if possible
  
    constructor(private userService: UserService, private http: HttpClient) { }
  
    ngOnInit(): void {
      this.http.get<any>("http://localhost:8083/log/details").subscribe(
        response => {
          console.log(response);
          // Transform hashmap into an array of objects
          this.users = Object.keys(response).map(key => ({
            userId: key,
            booksRented: response[key].length,
            userName:response.userName
          }));
        },
        error => {
          console.error('Getting user details failed', error);
        }
      );
    }
  
    toggleUserStatus(user: any) {
      user.userStatus = user.userStatus === 1 ? 0 : 1;
      // Implement logic to update status via API if required
    }
  }
  
