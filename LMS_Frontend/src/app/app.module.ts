import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { AddBookComponent } from './add-book/add-book.component';
import { ShowBookComponent } from './show-book/show-book.component';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { ReturnComponent } from './return/return.component';
import { RentBookComponent } from './rent-book/rent-book.component';
import { HeaderComponent } from './header/header.component';
import { UserComponent } from './user/user.component';
import { NoOfTimesRentedComponent } from './no-of-times-rented/no-of-times-rented.component';
import { FooterComponent } from './footer/footer.component';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user.service';
import { BookService } from './services/book.service';
import { FileUploadComponentComponent } from './file-upload-component/file-upload-component.component';
import { HomeComponent } from './home/home.component';
import { RentService } from './services/rent.service';
import { FileExcelService } from './services/file-excel.service';
import { LibrarianComponent } from './librarian/librarian.component';
import { AuthGuard } from './services/auth.guard';


@NgModule({
  declarations: [
    UserComponent,
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AddBookComponent,
    ShowBookComponent,
    ReturnComponent,
    RentBookComponent,
    HeaderComponent,
    FooterComponent,
    FileUploadComponentComponent,
    HomeComponent,
    NoOfTimesRentedComponent,
    LibrarianComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule ,
    MatDialogModule ,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatButtonModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    UserService,
    BookService,
    RentService,
    FileExcelService,
    AuthGuard,
    UserService,
    RentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
