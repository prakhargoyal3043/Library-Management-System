import { Injectable } from '@angular/core';
import * as XLSX from 'xlsx';
import { sendBook } from '../models/sendBook.model';

@Injectable({
  providedIn: 'root'
})
export class FileExcelService {

  constructor() { }

  async convertExcelToJson(file: File): Promise<sendBook[]> {
    return new Promise<sendBook[]>((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = (event: any) => {
        const data = new Uint8Array(event.target.result);
        const workbook = XLSX.read(data, { type: 'array' });
        const sheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[sheetName];
        const json: any[] = XLSX.utils.sheet_to_json(worksheet, { raw: false });

        // Map JSON to Book model
        const books: sendBook[] = json.map(item => ({
          bookId : item.bookId,
          bookName: item.bookName,
          bookISBNNo: item.bookISBNNo,
          bookAuthor: item.bookAuthor,
          bookPublisher: item.bookPublisher,
          bookDescription: item.bookDescription,
          bookCategory: item.bookCategory,
          bookPrice: parseFloat(item.bookPrice), // Convert to number
          bookEdition: item.bookEdition,
          bookImageUrl: item.bookImageUrl
        }));
        resolve(books);
      };
      reader.onerror = (error) => reject(error);
      reader.readAsArrayBuffer(file);
    });
  }


}
