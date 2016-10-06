import { Component, OnInit }              from '@angular/core';
import { Output, EventEmitter }           from '@angular/core';
import { FormsModule }                    from '@angular/forms';

import './rxjs-operators';

import { AppService }                     from './app.service';
import { Book }                           from './shared/book.model';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  providers: [ AppService ]
})
export class AppComponent implements OnInit{
//@Output() test = new EventEmitter();

  book: Book[];
  errorMessage: string;

  constructor (private AppService: AppService){}

  //title = 'hahahhahaa';

  ngOnInit(){
      this.getBooks();
  }

  getBooks(){
    return this.AppService.getBookServer()
        .subscribe(
          paper => this.book = paper,
          error => this.errorMessage = <any>error,
          //error => console.log("Error HTTP GET Service!!"),
          () => console.log("GET DONE")
        );
  }

  addBooks(addTitle: string, addAuthor: string, addDescrip: string, addPrice: number){  
    this.AppService.addBookServer(addTitle, addAuthor, addDescrip, addPrice)
          .subscribe(
            data => this.book ? location.reload() :
            //error => console.log("Error HTTP POST Service!!"),
            error => this.errorMessage = <any>error,
            () => console.log("POST DONE")
          );
  }

  removeBook(bookId: any){
    if(confirm("Are you sure you want to delete book " + bookId + " ?"))
      this.AppService.removeBookServer(bookId)
            .subscribe(
              data => location.reload()
            );
  }

  deleteALL(){
    if(confirm("Are you sure you want to delete all?"))
      this.AppService.removeALL()
            .subscribe(
              data => location.reload()
            );
  }

  updateBook(addTitle: string, addAuthor: string, addDescrip: string, addPrice: number){
    this.AppService.updateBookServer(addTitle, addAuthor, addDescrip, addPrice)
          .subscribe(
            data => this.book ? location.reload() :
            error => this.errorMessage = <any>error,
            () => console.log("PUT DONE")
          );
  }
  

}
