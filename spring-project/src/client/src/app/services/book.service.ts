import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookModel } from '../models/book.model';
import { AuthService } from './auth.service';

@Injectable()
export class BookService {
  constructor(
    private logger: NGXLogger,
    private http: HttpClient,
    private auth: AuthService,
  ) {
  }

  public loadBooks(): Observable<BookModel[]> {
    return this.http
      .get<BookModel[]>('/api/book', {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: BookModel[]) => data));
  }

  public loadBooksBySearch(search: string): Observable<BookModel[]> {
    return this.http
      .get<BookModel[]>('/api/book', {
        params: {'search': search},
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: BookModel[]) => data));
  }

  public loadBookById(payload: string): Observable<BookModel> {
    return this.http
      .get<BookModel>(`/api/book/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: BookModel) => data));
  }

  public createBook(payload: BookModel): Observable<BookModel> {
    return this.http
      .post<BookModel>('/api/book', payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: BookModel) => data));
  }

  public updateBook(payload: BookModel): Observable<BookModel> {
    return this.http
      .put<BookModel>(`/api/book/${payload.id}`, payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: BookModel) => data));
  }

  public deleteBook(payload: string) {
    return this.http
      .delete<string>(`/api/book/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: string) => data));
  }
}
