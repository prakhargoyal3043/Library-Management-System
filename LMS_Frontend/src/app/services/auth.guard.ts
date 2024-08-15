import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRoles = route.data['token'];
    const userRole = localStorage.getItem('token'); // Retrieve user's role from storage

    if (expectedRoles.includes(userRole)) {
      // User has the required role, allow access
      return true;
    } else {
      // User does not have the required role, redirect to appropriate route
      if (userRole === '898767934') {
        this.router.navigate(['/home']); // Redirect user to home page
      } else if (userRole === '765897953') {
        this.router.navigate(['/librarian']); // Redirect librarian to librarian component
      } else {
        this.router.navigate(['/login']); // Redirect others to login page or access denied page
      }
      return false;
    }

  }

}
