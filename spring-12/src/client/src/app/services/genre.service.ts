import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { GenreModel } from '../models/genre.model';

@Injectable()
export class GenreService {
  constructor(
    private http: HttpClient,
  ) {
  }

  public loadGenres(): Observable<GenreModel[]> {
    return this.http
      .get<GenreModel[]>('/api/genre')
      .pipe(map((data: GenreModel[]) => data));
  }

  public loadGenreById(payload: string): Observable<GenreModel> {
    return this.http
      .get<GenreModel>(`/api/genre/${payload}`)
      .pipe(map((data: GenreModel) => data));
  }

  public createGenre(payload: GenreModel): Observable<GenreModel> {
    return this.http
      .post<GenreModel>('/api/genre', payload)
      .pipe(map((data: GenreModel) => data));
  }

  public updateGenre(payload: GenreModel): Observable<GenreModel> {
    return this.http
      .put<GenreModel>(`/api/genre/${payload.id}`, payload)
      .pipe(map((data: GenreModel) => data));
  }

  public deleteGenre(payload: string) {
    return this.http
      .delete<string>(`/api/genre/${payload}`)
      .pipe(map((data: string) => data));
  }
}
