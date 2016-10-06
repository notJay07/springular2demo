import { Injectable }                                                      from '@angular/core';
import { Http, Response }                                                  from '@angular/http';
import { Headers, RequestOptions }                                         from '@angular/http';
import { Observable }                                                      from 'rxjs/Observable';

import { Book }                                                            from './shared/book.model';
//import { environment }                                                     from './environment';

@Injectable()
export class AppService {

  private _baseUrl:string = 'http://localhost:8080/books';

  constructor(private _http: Http){}

  getBookServer(): Observable<Book[]> {
    return this._http.get(this._baseUrl)
        //.map(res => { return res.json() });
        .map(this.extractData)
        .catch(this.handleError);
  }

  addBookServer(title: string, author: string, description: string, price: number){
    let body = JSON.stringify({ "title": title, "author": author, "description": description, "price": price });
    console.log(body);
    let headers = new Headers({ "Content-Type": "application/json" });
    let options = new RequestOptions({ headers: headers });

    return this._http.post(this._baseUrl, body, options)
        .map(res => { return res.json() });
  }

  removeBookServer(id: any){
    let headers = new Headers({ "Content-Type": "application/json" });

    return this._http.delete(`${this._baseUrl}/${id}`, headers)
        .map(res => { return res.json() });
  }

  removeALL(){
    let headers = new Headers({ "Content-Type": "application/json" });

    return this._http.delete(this._baseUrl, headers)
        .map(res => { return res.json() });
  }

  updateBookServer(title: string, author: string, description: string, price: number){
    let body = JSON.stringify({ "title": title, "author": author, "description": description, "price": price });
    console.log(body);
    let headers = new Headers({ "Content-Type": "application/json" });
    let options = new RequestOptions({ headers: headers });

    return this._http.patch(this._baseUrl, body, options)
        .map(res => { return res.json() });

  }

  private extractData(res: Response){
    let body = res.json();
    console.log(body.data);
    return body || null;
  }
  
  private handleError(error: Response){
    console.error(error);
    return Observable.throw(error.json().error || 'Server Error');
  }

}
