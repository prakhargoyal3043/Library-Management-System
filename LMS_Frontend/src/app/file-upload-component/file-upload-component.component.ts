import { Component } from '@angular/core';
import { FileExcelService } from '../services/file-excel.service';
import { HttpClient } from '@angular/common/http';
import { sendBook } from '../models/sendBook.model';

@Component({
  selector: 'app-file-upload-component',
  templateUrl: './file-upload-component.component.html',
  styleUrls: ['./file-upload-component.component.css']
})
export class FileUploadComponentComponent {

  fileData: sendBook[] = [];

  constructor(private fileExcelService: FileExcelService, private httpClient: HttpClient) { }

  onDragOver(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    (event.target as HTMLElement).classList.add('drag-over');
  }

  onDragLeave(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    (event.target as HTMLElement).classList.remove('drag-over');
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    (event.target as HTMLElement).classList.remove('drag-over');

    const files = event.dataTransfer?.files;
    if (files && files.length > 0) {
      this.handleFile(files[0]);
    }
  }

  onFileSelect(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.handleFile(input.files[0]);
    }
  }

  handleFile(file: File) {
    this.fileExcelService.convertExcelToJson(file).then((data: sendBook[]) => {
      this.fileData = data;
    });
  }

  onUpload() {
    this.httpClient.post("http://localhost:8083/books/bulkupload", this.fileData)
      .subscribe(
        response => {
          console.log('Upload successful', response);
          alert("Uploaded Successfully.");
        },
        error => {
          console.error('Error uploading books', error);
          alert("Error uploading books");
        }
      );
      this.httpClient.get("http://localhost:8083/books").subscribe(
        Response => {
          console.log(Response);
        },
        error => {
          console.error('getting all books failed',error);
        }
      );
  }
}
