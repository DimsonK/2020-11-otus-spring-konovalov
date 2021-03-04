import { Injectable } from '@angular/core';

import { Actions, Effect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';

import { Observable, of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { AuthorModel } from '../../models/author.model';

import { AuthorService } from '../../services/author.service';
import * as authorActions from './actions';

@Injectable()
export class AuthorEffect {
  constructor(
    private actions$: Actions,
    private authorService: AuthorService
  ) {
  }

  @Effect()
  loadAuthors$: Observable<Action> = this.actions$.pipe(
    ofType<authorActions.LoadAuthors>(
      authorActions.AuthorActionTypes.LOAD_AUTHORS
    ),
    mergeMap((action: authorActions.LoadAuthors) =>
      this.authorService.loadAuthors().pipe(
        map(
          (authors: AuthorModel[]) =>
            new authorActions.LoadAuthorsSuccess(authors)
        ),
        catchError((err) =>
          of(
            new authorActions.LoadAuthorsFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  loadAuthor$: Observable<Action> = this.actions$.pipe(
    ofType<authorActions.LoadAuthor>(
      authorActions.AuthorActionTypes.LOAD_AUTHOR
    ),
    mergeMap((action: authorActions.LoadAuthor) =>
      this.authorService.loadAuthorById(action.payload).pipe(
        map(
          (author: AuthorModel) =>
            new authorActions.LoadAuthorSuccess(author)
        ),
        catchError((err) =>
          of(
            new authorActions.LoadAuthorFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  createAuthor$: Observable<Action> = this.actions$.pipe(
    ofType<authorActions.CreateAuthor>(
      authorActions.AuthorActionTypes.CREATE_AUTHOR
    ),
    map((action: authorActions.CreateAuthor) => action.payload),
    mergeMap((author: AuthorModel) =>
      this.authorService.createAuthor(author).pipe(
        map(
          (newAuthor: AuthorModel) =>
            new authorActions.CreateAuthorSuccess(newAuthor)
        ),
        catchError((err) =>
          of(
            new authorActions.CreateAuthorFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  updateAuthor$: Observable<Action> = this.actions$.pipe(
    ofType<authorActions.UpdateAuthor>(
      authorActions.AuthorActionTypes.UPDATE_AUTHOR
    ),
    map((action: authorActions.UpdateAuthor) => action.payload),
    mergeMap((author: AuthorModel) =>
      this.authorService.updateAuthor(author).pipe(
        map(
          (updateAuthor: AuthorModel) =>
            new authorActions.UpdateAuthorSuccess({
              id: updateAuthor.id,
              changes: updateAuthor,
            })
        ),
        catchError((err) =>
          of(
            new authorActions.UpdateAuthorFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  deleteAuthor$: Observable<Action> = this.actions$.pipe(
    ofType<authorActions.DeleteAuthor>(
      authorActions.AuthorActionTypes.DELETE_AUTHOR
    ),
    map((action: authorActions.DeleteAuthor) => action.payload),
    mergeMap((id: string) =>
      this.authorService.deleteAuthor(id).pipe(
        map(() => new authorActions.DeleteAuthorSuccess(id)),
        catchError((err) =>
          of(
            new authorActions.DeleteAuthorFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );
}
