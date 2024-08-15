import { Component, OnInit } from '@angular/core';
import { UserService, RenteeLogDTO } from '../services/user.service';
import { BookService } from '../services/book.service'; // Import the BookService
import { recieveBook } from '../models/recieveBook.model';
import { Observable, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';

interface BookRentStats {
  bookId: string;
  noOfTimes: number;
  revenueGenerated: number;
  bookPrice: number;
}

@Component({
  selector: 'app-no-of-times-rented',
  templateUrl: './no-of-times-rented.component.html',
  styleUrls: ['./no-of-times-rented.component.css']
})
export class NoOfTimesRentedComponent implements OnInit {
  no: BookRentStats[] = [];

  constructor(private userService: UserService, private bookService: BookService) { }

  ngOnInit(): void {
    this.userService.getRenteeLogs().subscribe(
      (data: RenteeLogDTO[]) => {
        console.log('Fetched data:', data); // Log the fetched data
        this.processRentStats(data);
      },
      (error: any) => {
        console.error('Error fetching rentee logs', error);
      }
    );
  }

  processRentStats(data: RenteeLogDTO[]): void {
    const bookStatsMap = new Map<string, BookRentStats>();

    data.forEach(log => {
      const bookId = log.bookId.toString(); // Convert bookId to string for Map key
      const amountPaid = log.amountPaid || 0; // Ensure amountPaid is correctly accessed and default to 0 if not present

      if (bookStatsMap.has(bookId)) {
        const stats = bookStatsMap.get(bookId)!;
        stats.noOfTimes++;
        stats.revenueGenerated += amountPaid; // Add the amountPaid to the total revenue
      } else {
        bookStatsMap.set(bookId, {
          bookId: bookId,
          noOfTimes: 1,
          revenueGenerated: amountPaid, // Initialize with the first rental price
          bookPrice: 0 // Temporary placeholder, will update with actual price later
        });
      }
    });

    // Fetch book details including the price
    this.fetchBookPrices(bookStatsMap).subscribe(
      (updatedStats: BookRentStats[]) => {
        this.no = updatedStats;
        console.log('Processed data with prices:', this.no); // Log the processed data with prices
      },
      (error: any) => {
        console.error('Error fetching book prices', error);
      }
    );
  }

  fetchBookPrices(bookStatsMap: Map<string, BookRentStats>): Observable<BookRentStats[]> {
    const bookDetailsObservables = Array.from(bookStatsMap.keys()).map(bookId => {
      return this.bookService.getBookById(parseInt(bookId)).pipe(
        map((bookDetails: recieveBook) => {
          const stats = bookStatsMap.get(bookId)!;
          if (bookDetails) {
            stats.bookPrice = bookDetails.bookPrice; // Update bookPrice with actual price from book details
          }
          return stats;
        })
      );
    });

    return forkJoin(bookDetailsObservables);
  }
}
