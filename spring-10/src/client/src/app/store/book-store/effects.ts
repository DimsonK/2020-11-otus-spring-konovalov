import { Injectable } from '@angular/core';

import { Actions, Effect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';

import { Observable, of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { BookModel } from '../../models/book.model';

import { BookService } from '../../services/book.service';
import * as bookActions from './actions';

@Injectable()
export class BookEffect {
  constructor(
    private actions$: Actions,
    private bookService: BookService
  ) {
  }

  @Effect()
  loadBooks$: Observable<Action> = this.actions$.pipe(
    ofType<bookActions.LoadBooks>(
      bookActions.BookActionTypes.LOAD_BOOKS
    ),
    mergeMap((action: bookActions.LoadBooks) =>
      this.bookService.loadBooks().pipe(
        map(
          (books: BookModel[]) =>
            new bookActions.LoadBooksSuccess(books)
        ),
        catchError((err) =>
          of(
            new bookActions.LoadBooksFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  loadBook$: Observable<Action> = this.actions$.pipe(
    ofType<bookActions.LoadBook>(
      bookActions.BookActionTypes.LOAD_BOOK
    ),
    mergeMap((action: bookActions.LoadBook) =>
      this.bookService.loadBookById(action.payload).pipe(
        map(
          (book: BookModel) =>
            new bookActions.LoadBookSuccess(book)
        ),
        catchError((err) =>
          of(
            new bookActions.LoadBookFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  createBook$: Observable<Action> = this.actions$.pipe(
    ofType<bookActions.CreateBook>(
      bookActions.BookActionTypes.CREATE_BOOK
    ),
    map((action: bookActions.CreateBook) => action.payload),
    mergeMap((book: BookModel) =>
      this.bookService.createBook(book).pipe(
        map(
          (newBook: BookModel) =>
            new bookActions.CreateBookSuccess(newBook)
        ),
        catchError((err) =>
          of(
            new bookActions.CreateBookFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  updateBook$: Observable<Action> = this.actions$.pipe(
    ofType<bookActions.UpdateBook>(
      bookActions.BookActionTypes.UPDATE_BOOK
    ),
    map((action: bookActions.UpdateBook) => action.payload),
    mergeMap((book: BookModel) =>
      this.bookService.updateBook(book).pipe(
        map(
          (updateBook: BookModel) =>
            new bookActions.UpdateBookSuccess({
              id: updateBook.id,
              changes: updateBook,
            })
        ),
        catchError((err) =>
          of(
            new bookActions.UpdateBookFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  deleteBook$: Observable<Action> = this.actions$.pipe(
    ofType<bookActions.DeleteBook>(
      bookActions.BookActionTypes.DELETE_BOOK
    ),
    map((action: bookActions.DeleteBook) => action.payload),
    mergeMap((id: string) =>
      this.bookService.deleteBook(id).pipe(
        map(() => new bookActions.DeleteBookSuccess(id)),
        catchError((err) =>
          of(
            new bookActions.DeleteBookFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );
}
