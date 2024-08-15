import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { HashService } from '../services/hash.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';
  

  constructor(private authService: AuthService, private router: Router, private hashService: HashService) {}

  login(): void {
    this.errorMessage = ''; // Clear previous error message
    const hashedPassword = this.hashService.hashit(this.password);
    this.authService.login(this.username, hashedPassword)
      .subscribe(
        token => {
          localStorage.setItem('token', token); // Store token in local storage
          localStorage.setItem('username', this.username);
          this.router.navigate(['/home']);
        },
        error => {
          this.errorMessage = 'Invalid username or password';
        }
      );
  }
}
