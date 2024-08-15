import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { sendUser } from '../models/sendUser.model';
import { FormsModule } from '@angular/forms';
import { HashService } from '../services/hash.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  // FORMAL NORMAL APP
  userDetails: sendUser = {
    userName: '',
    userEmail: '',
    userPhoneNumber: 0,
    userGovId: '',
    userPassword: '',
    userAddress: '',
    
  };
  confirmPassword: string='';

  // FOR USING iTURMERIC 
  // userDetails: sendUser = {
  //   userId : '',
  //   userName: '',
  //   userEmail: '',
  //   userPhoneNumber: 0,
  //   userGovId: '',
  //   userPassword: '',
  //   userAddress: '',
    
  // };

  constructor(
    private http: HttpClient,
    private router: Router,
    private hashservice: HashService,
    private authservice: AuthService
  ) { }


  // FOR NORMAL APP
  register() {
    this.userDetails.userPassword = this.hashservice.hashit(this.userDetails.userPassword);
    this.http.post('http://localhost:8083/users', this.userDetails)
      .subscribe(
        response => {
          console.log('Registration successful:', response);
          this.router.navigate(['/login']).then(() => {
            alert('User registered successfully!');
          });
        },
        error => {
          console.error('Error registering:', error);
        }
      );
  }


  // FOR USING iTURMERIC
  // register() {

  //   const requestBody = {
  //     LMS_USERS: [
  //       {
  //         userId : this.userDetails.userId,
  //         userName: this.userDetails.userName,
  //         userEmail: this.userDetails.userEmail,
  //         userPhoneNumber: this.userDetails.userPhoneNumber,
  //         userGovId: this.userDetails.userGovId,
  //         userPassword: this.userDetails.userPassword,
  //         userAddress: this.userDetails.userAddress
  //         }
  //         ]
  //         };
  //   this.userDetails.userPassword = this.hashservice.hashit(this.userDetails.userPassword);
  //   this.http.post('http://192.168.63.119:40292/olive/publisher/LMS_USERS', requestBody)
  //     .subscribe(
  //       response => {
  //         console.log('Registration successful:', response);
  //         this.router.navigate(['/login']).then(() => {
  //           alert('User registered successfully!');
  //         });
  //       },
  //       error => {
  //         console.error('Error registering:', error);
  //       }
  //     );
  // }
}
