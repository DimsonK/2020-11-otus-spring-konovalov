import { Update } from '@ngrx/entity';
import { Action } from '@ngrx/store';

import { BookModel } from '../../models/book.model';


export enum BookActionTypes {
  LOAD_BOOKS = '[Book/API Load Books Request',
  LOAD_BOOKS_SUCCESS = '[Book/API Load Books Success',
  LOAD_BOOKS_FAIL = '[Book/API Load Books Failure',
  LOAD_BOOKS_LIKE_NAME = '[Book/API Load Books like name Request',
  LOAD_BOOKS_LIKE_NAME_SUCCESS = '[Book/API Load Books like name Success',
  LOAD_BOOKS_LIKE_NAME_FAIL = '[Book/API Load Books like name Failure',
  LOAD_BOOK = '[Book/API Load Book Request',
  LOAD_BOOK_SUCCESS = '[Book/API Load Book Success',
  LOAD_BOOK_FAIL = '[Book/API Load Book Failure',
  CREATE_BOOK = '[Book/API Create Book Request',
  CREATE_BOOK_SUCCESS = '[Book/API Create Book Success',
  CREATE_BOOK_FAIL = '[Book/API Create Book Failure',
  UPDATE_BOOK = '[Book/API Update Book Request',
  UPDATE_BOOK_SUCCESS = '[Book/API Update Book Success',
  UPDATE_BOOK_FAIL = '[Book/API Update Book Failure',
  DELETE_BOOK = '[Book/API Delete Book Request',
  DELETE_BOOK_SUCCESS = '[Book/API Delete Book Success',
  DELETE_BOOK_FAIL = '[Book/API Delete Book Failure',
}

export class LoadBooks implements Action {
  readonly type = BookActionTypes.LOAD_BOOKS;
}

export class LoadBooksSuccess implements Action {
  readonly type = BookActionTypes.LOAD_BOOKS_SUCCESS;

  constructor(public payload: BookModel[]) {
  }
}

export class LoadBooksFail implements Action {
  readonly type = BookActionTypes.LOAD_BOOKS_FAIL;

  constructor(public payload: string) {
  }
}

//
export class LoadBooksLikeName implements Action {
  readonly type = BookActionTypes.LOAD_BOOKS_LIKE_NAME;

  constructor(public payload: string) {
  }
}

export class LoadBooksLikeNameSuccess implements Action {
  readonly type = BookActionTypes.LOAD_BOOKS_LIKE_NAME_SUCCESS;

  constructor(public payload: BookModel[]) {
  }
}

export class LoadBooksLikeNameFail implements Action {
  readonly type = BookActionTypes.LOAD_BOOKS_LIKE_NAME_FAIL;

  constructor(public payload: string) {
  }
}


//
export class LoadBook implements Action {
  readonly type = BookActionTypes.LOAD_BOOK;

  constructor(public payload: string) {
  }
}

export class LoadBookSuccess implements Action {
  readonly type = BookActionTypes.LOAD_BOOK_SUCCESS;

  constructor(public payload: BookModel) {
  }
}

export class LoadBookFail implements Action {
  readonly type = BookActionTypes.LOAD_BOOK_FAIL;

  constructor(public payload: string) {
  }
}

//
export class CreateBook implements Action {
  readonly type = BookActionTypes.CREATE_BOOK;

  constructor(public payload: BookModel) {
  }
}

export class CreateBookSuccess implements Action {
  readonly type = BookActionTypes.CREATE_BOOK_SUCCESS;

  constructor(public payload: BookModel) {
  }
}

export class CreateBookFail implements Action {
  readonly type = BookActionTypes.CREATE_BOOK_FAIL;

  constructor(public payload: string) {
  }
}

//
export class UpdateBook implements Action {
  readonly type = BookActionTypes.UPDATE_BOOK;

  constructor(public payload: BookModel) {
  }
}

export class UpdateBookSuccess implements Action {
  readonly type = BookActionTypes.UPDATE_BOOK_SUCCESS;

  constructor(public payload: Update<BookModel>) {
  }
}

export class UpdateBookFail implements Action {
  readonly type = BookActionTypes.UPDATE_BOOK_FAIL;

  constructor(public payload: string) {
  }
}

//
export class DeleteBook implements Action {
  readonly type = BookActionTypes.DELETE_BOOK;

  constructor(public payload: string) {
  }
}

export class DeleteBookSuccess implements Action {
  readonly type = BookActionTypes.DELETE_BOOK_SUCCESS;

  constructor(public payload: string) {
  }
}

export class DeleteBookFail implements Action {
  readonly type = BookActionTypes.DELETE_BOOK_FAIL;

  constructor(public payload: string) {
  }
}

export type Actions =
  | LoadBooks
  | LoadBooksSuccess
  | LoadBooksFail
  | LoadBooksLikeName
  | LoadBooksLikeNameSuccess
  | LoadBooksLikeNameFail
  | LoadBook
  | LoadBookSuccess
  | LoadBookFail
  | CreateBook
  | CreateBookSuccess
  | CreateBookFail
  | UpdateBook
  | UpdateBookSuccess
  | UpdateBookFail
  | DeleteBook
  | DeleteBookSuccess
  | DeleteBookFail;
