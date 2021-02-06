import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookModel } from '../models/book.model';

@Injectable()
export class BookService {
  constructor(
    private http: HttpClient,
  ) {
  }

  public loadBooks(): Observable<BookModel[]> {
    return this.http
      .get<BookModel[]>('/api/book')
      .pipe(map((data: BookModel[]) => data));
  }

  public loadBookById(payload: string): Observable<BookModel> {
    return this.http
      .get<BookModel>(`/api/book/${payload}`)
      .pipe(map((data: BookModel) => data));
  }

  public createBook(payload: BookModel): Observable<BookModel> {
    return this.http
      .post<BookModel>('/api/book', payload)
      .pipe(map((data: BookModel) => data));
  }

  public updateBook(payload: BookModel): Observable<BookModel> {
    return this.http
      .put<BookModel>(`/api/book/${payload.id}`, payload)
      .pipe(map((data: BookModel) => data));
  }

  public deleteBook(payload: string) {
    return this.http
      .delete<string>(`/api/book/${payload}`)
      .pipe(map((data: string) => data));
  }
}
