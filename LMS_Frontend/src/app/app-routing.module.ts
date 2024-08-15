// import { NgModule } from '@angular/core';
// import { RouterModule, Routes } from '@angular/router';
// import { LoginComponent } from './login/login.component';
// import { HomeComponent } from './home/home.component';
// import { RegisterComponent } from './register/register.component';
// import { AddBookComponent } from './add-book/add-book.component';
// import { ShowBookComponent } from './show-book/show-book.component';
// import { RentBookComponent } from './rent-book/rent-book.component';
// import { ReturnComponent } from './return/return.component';
// import { NoOfTimesRentedComponent } from './no-of-times-rented/no-of-times-rented.component';
// import { UserComponent } from './user/user.component';
// import { FileUploadComponentComponent } from './file-upload-component/file-upload-component.component';
// import { LibrarianComponent } from './librarian/librarian.component';

// const routes: Routes = [
//   { path: '', redirectTo: '/home', pathMatch: 'full' }, // Redirect default to home
//   { path: 'login', component: LoginComponent },
//   { path: 'home', component: HomeComponent },
//   { path: 'register', component: RegisterComponent },
//   { path: 'add-book', component: AddBookComponent },
//   { path: 'show-book/:id', component: ShowBookComponent },
//   { path: 'rent-book/:id', component: RentBookComponent },
//   { path: 'return', component: ReturnComponent},
//   { path: 'bulkupload', component: FileUploadComponentComponent},
//   { path: 'user', component: UserComponent},
//   { path: 'rented', component:NoOfTimesRentedComponent},
//   { path: 'librarian', component:LibrarianComponent},
//   { path: '**', redirectTo: '/home' } // Redirect any other unknown routes to home
  
// ];

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }



import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './services/auth.guard';
import { AddBookComponent } from './add-book/add-book.component';
import { FileUploadComponentComponent } from './file-upload-component/file-upload-component.component';
import { HomeComponent } from './home/home.component';
import { LibrarianComponent } from './librarian/librarian.component';
import { LoginComponent } from './login/login.component';
import { NoOfTimesRentedComponent } from './no-of-times-rented/no-of-times-rented.component';
import { RegisterComponent } from './register/register.component';
import { RentBookComponent } from './rent-book/rent-book.component';
import { ReturnComponent } from './return/return.component';
import { ShowBookComponent } from './show-book/show-book.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard],
    data: { token: ['898767934'] } // Accessible to user
  },
  {
    path: 'rent-book/:id',
    component: RentBookComponent,
    canActivate: [AuthGuard],
    data: { token: ['898767934'] } // Accessible to user
  },
  {
    path: 'return',
    component: ReturnComponent,
    canActivate: [AuthGuard],
    data: { token: ['898767934'] } // Accessible to user
  },
  {
    path: 'show-book/:id',
    component: ShowBookComponent,
    canActivate: [AuthGuard],
    data: { token: ['898767934','765897953'] } // Accessible to both
  },
  {
    path: 'add-book',
    component: AddBookComponent,
    canActivate: [AuthGuard],
    data: { token: ['765897953'] } // Accessible only to librarian
  },
  {
    path: 'bulkupload',
    component: FileUploadComponentComponent,
    canActivate: [AuthGuard],
    data: { token: ['765897953'] } // Accessible only to librarian
  },
  {
    path: 'librarian',
    component: LibrarianComponent,
    canActivate: [AuthGuard],
    data: { token: ['765897953'] } // Accessible only to librarian
  },
  {
    path: 'rented',
    component: NoOfTimesRentedComponent,
    canActivate: [AuthGuard],
    data: { token: ['765897953'] } // Accessible only to librarian
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: [AuthGuard],
    data: { token: ['765897953'] } // Accessible only to librarian
  },
  { path: '**', redirectTo: '/login' } // Redirect to login for any unknown routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

