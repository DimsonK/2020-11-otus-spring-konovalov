import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthorModel } from '../models/author.model';
import { AuthService } from './auth.service';

@Injectable()
export class AuthorService {
  constructor(
    private logger: NGXLogger,
    private http: HttpClient,
    private auth: AuthService,
  ) {
  }

  public loadAuthors(): Observable<AuthorModel[]> {
    return this.http
      .get<AuthorModel[]>('/api/author', {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: AuthorModel[]) => data));
  }

  public loadAuthorById(payload: string): Observable<AuthorModel> {
    return this.http
      .get<AuthorModel>(`/api/author/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: AuthorModel) => data));
  }

  public createAuthor(payload: AuthorModel): Observable<AuthorModel> {
    return this.http
      .post<AuthorModel>('/api/author', payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: AuthorModel) => data));
  }

  public updateAuthor(payload: AuthorModel): Observable<AuthorModel> {
    return this.http
      .put<AuthorModel>(`/api/author/${payload.id}`, payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: AuthorModel) => data));
  }

  public deleteAuthor(payload: string) {
    return this.http
      .delete<string>(`/api/author/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: string) => data));
  }
}
