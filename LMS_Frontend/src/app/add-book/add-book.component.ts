import { Component } from '@angular/core';
import { sendBook } from '../models/sendBook.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent {

  constructor(private http : HttpClient, private router : Router) {}


  // FOR USING iTURMERIC 
  // bookToAdd:sendBook ={  
  //   bookId: '',
  // bookName: '',
  // bookISBNNo: '',
  // bookAuthor: '',
  // bookPublisher: '',
  // bookDescription: '',
  // bookCategory: '',
  // bookPrice: 0,
  // bookEdition: '',
  // bookImageUrl: ''
  // }
  
  // saveBook() {
  //   const requestBody = {
  //     LMS_BOOKS: [
  //       {
  //         bookId: this.bookToAdd.bookId, // You might want to generate a unique ID or remove this field if it's auto-generated on the server
  //         bookName: this.bookToAdd.bookName,
  //         bookAuthor: this.bookToAdd.bookAuthor,
  //         bookPublisher: this.bookToAdd.bookPublisher,
  //         bookDesciption: this.bookToAdd.bookDescription,
  //         bookISBNNo: this.bookToAdd.bookISBNNo,
  //         bookCategory: this.bookToAdd.bookCategory,
  //         bookPrice: this.bookToAdd.bookPrice,
  //         bookStatus: 1, // You might want to make this field configurable or remove it if it's always 1
  //         bookEdition: this.bookToAdd.bookEdition,
  //         bookLocation: "2", // You might want to make this field configurable or remove it if it's always 2
  //         bookImageUrl: this.bookToAdd.bookImageUrl
  //       }
  //     ]
  //   };


  //   this.http.post('http://192.168.63.119:40292/olive/publisher/LMS_BOOKS', requestBody)
  //   .subscribe(
  //     response => {
  //       console.log('Book added successfully :', response);
  //         this.router.navigate(['/home']).then(() => {
  //           alert('Book added successfully!');
  //         });
  //       },
      
  //     error => {
  //       console.error('Error Adding book :', error);
  //     }
  //   );
  //   this.resetForm();
  // }

  // FOR NORMAL APP:

  bookToAdd:sendBook ={  
    bookName: '',
    bookISBNNo: '',
    bookAuthor: '',
    bookPublisher: '',
    bookDescription: '',
    bookCategory: '',
    bookPrice: 0,
    bookEdition: '',
    bookImageUrl: ''
    }
    
  
    saveBook() {
      this.http.post('http://localhost:8083/books', this.bookToAdd)
      .subscribe(
        response => {
          console.log('Book added successfully :', response);
            this.router.navigate(['/home']).then(() => {
              alert('Book added successfully!');
            });
          },
        
        error => {
          console.error('Error Adding book :', error);
        }
      );
      this.resetForm();
    }

  resetForm() {
    this.bookToAdd.bookName = '',
    this.bookToAdd.bookISBNNo = '',
    this.bookToAdd.bookAuthor = '',
    this.bookToAdd.bookPublisher = '',
    this.bookToAdd.bookEdition = '',
    this.bookToAdd.bookPrice = 0,
    this.bookToAdd.bookDescription = '',
    this.bookToAdd.bookCategory = '',
    this.bookToAdd.bookImageUrl= '';
  }
}
