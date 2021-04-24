import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthorModel } from '../models/author.model';

@Injectable()
export class AuthorService {
  constructor(
    private http: HttpClient,
  ) {
  }

  public loadAuthors(): Observable<AuthorModel[]> {
    return this.http
      .get<AuthorModel[]>('/api/author')
      .pipe(map((data: AuthorModel[]) => data));
  }

  public loadAuthorById(payload: string): Observable<AuthorModel> {
    return this.http
      .get<AuthorModel>(`/api/author/${payload}`)
      .pipe(map((data: AuthorModel) => data));
  }

  public createAuthor(payload: AuthorModel): Observable<AuthorModel> {
    return this.http
      .post<AuthorModel>('/api/author', payload)
      .pipe(map((data: AuthorModel) => data));
  }

  public updateAuthor(payload: AuthorModel): Observable<AuthorModel> {
    return this.http
      .put<AuthorModel>(`/api/author/${payload.id}`, payload)
      .pipe(map((data: AuthorModel) => data));
  }

  public deleteAuthor(payload: string) {
    return this.http
      .delete<string>(`/api/author/${payload}`)
      .pipe(map((data: string) => data));
  }
}
