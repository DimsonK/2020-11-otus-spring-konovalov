import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { GenreModel } from '../models/genre.model';
import { AuthService } from './auth.service';

@Injectable()
export class GenreService {
  constructor(
    private logger: NGXLogger,
    private http: HttpClient,
    private auth: AuthService,
  ) {
  }

  public loadGenres(): Observable<GenreModel[]> {
    return this.http
      .get<GenreModel[]>('/api/genre', {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: GenreModel[]) => data));
  }

  public loadGenreById(payload: string): Observable<GenreModel> {
    return this.http
      .get<GenreModel>(`/api/genre/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: GenreModel) => data));
  }

  public createGenre(payload: GenreModel): Observable<GenreModel> {
    return this.http
      .post<GenreModel>('/api/genre', payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: GenreModel) => data));
  }

  public updateGenre(payload: GenreModel): Observable<GenreModel> {
    return this.http
      .put<GenreModel>(`/api/genre/${payload.id}`, payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: GenreModel) => data));
  }

  public deleteGenre(payload: string) {
    return this.http
      .delete<string>(`/api/genre/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: string) => data));
  }
}
