import { Update } from '@ngrx/entity';
import { Action } from '@ngrx/store';

import { GenreModel } from '../../models/genre.model';


export enum GenreActionTypes {
  LOAD_GENRES = '[Genre/API Load Genres Request',
  LOAD_GENRES_SUCCESS = '[Genre/API Load Genres Success',
  LOAD_GENRES_FAIL = '[Genre/API Load Genres Failure',
  LOAD_GENRE = '[Genre/API Load Genre Request',
  LOAD_GENRE_SUCCESS = '[Genre/API Load Genre Success',
  LOAD_GENRE_FAIL = '[Genre/API Load Genre Failure',
  CREATE_GENRE = '[Genre/API Create Genre Request',
  CREATE_GENRE_SUCCESS = '[Genre/API Create Genre Success',
  CREATE_GENRE_FAIL = '[Genre/API Create Genre Failure',
  UPDATE_GENRE = '[Genre/API Update Genre Request',
  UPDATE_GENRE_SUCCESS = '[Genre/API Update Genre Success',
  UPDATE_GENRE_FAIL = '[Genre/API Update Genre Failure',
  DELETE_GENRE = '[Genre/API Delete Genre Request',
  DELETE_GENRE_SUCCESS = '[Genre/API Delete Genre Success',
  DELETE_GENRE_FAIL = '[Genre/API Delete Genre Failure',
}

export class LoadGenres implements Action {
  readonly type = GenreActionTypes.LOAD_GENRES;
}

export class LoadGenresSuccess implements Action {
  readonly type = GenreActionTypes.LOAD_GENRES_SUCCESS;

  constructor(public payload: GenreModel[]) {
  }
}

export class LoadGenresFail implements Action {
  readonly type = GenreActionTypes.LOAD_GENRES_FAIL;

  constructor(public payload: string) {
  }
}

//
export class LoadGenre implements Action {
  readonly type = GenreActionTypes.LOAD_GENRE;

  constructor(public payload: string) {
  }
}

export class LoadGenreSuccess implements Action {
  readonly type = GenreActionTypes.LOAD_GENRE_SUCCESS;

  constructor(public payload: GenreModel) {
  }
}

export class LoadGenreFail implements Action {
  readonly type = GenreActionTypes.LOAD_GENRE_FAIL;

  constructor(public payload: string) {
  }
}

//
export class CreateGenre implements Action {
  readonly type = GenreActionTypes.CREATE_GENRE;

  constructor(public payload: GenreModel) {
  }
}

export class CreateGenreSuccess implements Action {
  readonly type = GenreActionTypes.CREATE_GENRE_SUCCESS;

  constructor(public payload: GenreModel) {
  }
}

export class CreateGenreFail implements Action {
  readonly type = GenreActionTypes.CREATE_GENRE_FAIL;

  constructor(public payload: string) {
  }
}

//
export class UpdateGenre implements Action {
  readonly type = GenreActionTypes.UPDATE_GENRE;

  constructor(public payload: GenreModel) {
  }
}

export class UpdateGenreSuccess implements Action {
  readonly type = GenreActionTypes.UPDATE_GENRE_SUCCESS;

  constructor(public payload: Update<GenreModel>) {
  }
}

export class UpdateGenreFail implements Action {
  readonly type = GenreActionTypes.UPDATE_GENRE_FAIL;

  constructor(public payload: string) {
  }
}

//
export class DeleteGenre implements Action {
  readonly type = GenreActionTypes.DELETE_GENRE;

  constructor(public payload: string) {
  }
}

export class DeleteGenreSuccess implements Action {
  readonly type = GenreActionTypes.DELETE_GENRE_SUCCESS;

  constructor(public payload: string) {
  }
}

export class DeleteGenreFail implements Action {
  readonly type = GenreActionTypes.DELETE_GENRE_FAIL;

  constructor(public payload: string) {
  }
}

export type Actions =
  | LoadGenres
  | LoadGenresSuccess
  | LoadGenresFail
  | LoadGenre
  | LoadGenreSuccess
  | LoadGenreFail
  | CreateGenre
  | CreateGenreSuccess
  | CreateGenreFail
  | UpdateGenre
  | UpdateGenreSuccess
  | UpdateGenreFail
  | DeleteGenre
  | DeleteGenreSuccess
  | DeleteGenreFail;
