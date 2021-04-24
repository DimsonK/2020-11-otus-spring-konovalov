import { Injectable } from '@angular/core';

import { Actions, Effect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';

import { Observable, of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { GenreModel } from '../../models/genre.model';

import { GenreService } from '../../services/genre.service';
import * as genreActions from './actions';

@Injectable()
export class GenreEffect {
  constructor(
    private actions$: Actions,
    private genreService: GenreService
  ) {
  }

  @Effect()
  loadGenres$: Observable<Action> = this.actions$.pipe(
    ofType<genreActions.LoadGenres>(
      genreActions.GenreActionTypes.LOAD_GENRES
    ),
    mergeMap((action: genreActions.LoadGenres) =>
      this.genreService.loadGenres().pipe(
        map(
          (genres: GenreModel[]) =>
            new genreActions.LoadGenresSuccess(genres)
        ),
        catchError((err) =>
          of(
            new genreActions.LoadGenresFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  loadGenre$: Observable<Action> = this.actions$.pipe(
    ofType<genreActions.LoadGenre>(
      genreActions.GenreActionTypes.LOAD_GENRE
    ),
    mergeMap((action: genreActions.LoadGenre) =>
      this.genreService.loadGenreById(action.payload).pipe(
        map(
          (genre: GenreModel) =>
            new genreActions.LoadGenreSuccess(genre)
        ),
        catchError((err) =>
          of(
            new genreActions.LoadGenreFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  createGenre$: Observable<Action> = this.actions$.pipe(
    ofType<genreActions.CreateGenre>(
      genreActions.GenreActionTypes.CREATE_GENRE
    ),
    map((action: genreActions.CreateGenre) => action.payload),
    mergeMap((genre: GenreModel) =>
      this.genreService.createGenre(genre).pipe(
        map(
          (newGenre: GenreModel) =>
            new genreActions.CreateGenreSuccess(newGenre)
        ),
        catchError((err) =>
          of(
            new genreActions.CreateGenreFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  updateGenre$: Observable<Action> = this.actions$.pipe(
    ofType<genreActions.UpdateGenre>(
      genreActions.GenreActionTypes.UPDATE_GENRE
    ),
    map((action: genreActions.UpdateGenre) => action.payload),
    mergeMap((genre: GenreModel) =>
      this.genreService.updateGenre(genre).pipe(
        map(
          (updateGenre: GenreModel) =>
            new genreActions.UpdateGenreSuccess({
              id: updateGenre.id,
              changes: updateGenre,
            })
        ),
        catchError((err) =>
          of(
            new genreActions.UpdateGenreFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  deleteGenre$: Observable<Action> = this.actions$.pipe(
    ofType<genreActions.DeleteGenre>(
      genreActions.GenreActionTypes.DELETE_GENRE
    ),
    map((action: genreActions.DeleteGenre) => action.payload),
    mergeMap((id: string) =>
      this.genreService.deleteGenre(id).pipe(
        map(() => new genreActions.DeleteGenreSuccess(id)),
        catchError((err) =>
          of(
            new genreActions.DeleteGenreFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );
}
