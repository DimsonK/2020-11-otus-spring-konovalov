import { Injectable } from '@angular/core';

import { Actions, Effect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';

import { Observable, of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { CommentModel } from '../../models/comment.model';

import { CommentService } from '../../services/comment.service';
import * as commentActions from './actions';

@Injectable()
export class CommentEffect {
  constructor(
    private actions$: Actions,
    private commentService: CommentService
  ) {
  }

  @Effect()
  loadComments$: Observable<Action> = this.actions$.pipe(
    ofType<commentActions.LoadComments>(
      commentActions.CommentActionTypes.LOAD_COMMENTS_BY_BOOK_ID
    ),
    mergeMap((action: commentActions.LoadComments) =>
      this.commentService.loadComments(action.payload).pipe(
        map(
          (comments: CommentModel[]) =>
            new commentActions.LoadCommentsSuccess(comments)
        ),
        catchError((err) =>
          of(
            new commentActions.LoadCommentsFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  loadComment$: Observable<Action> = this.actions$.pipe(
    ofType<commentActions.LoadComment>(
      commentActions.CommentActionTypes.LOAD_COMMENT
    ),
    mergeMap((action: commentActions.LoadComment) =>
      this.commentService.loadCommentById(action.payload).pipe(
        map(
          (comment: CommentModel) =>
            new commentActions.LoadCommentSuccess(comment)
        ),
        catchError((err) =>
          of(
            new commentActions.LoadCommentFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  createComment$: Observable<Action> = this.actions$.pipe(
    ofType<commentActions.CreateComment>(
      commentActions.CommentActionTypes.CREATE_COMMENT
    ),
    map((action: commentActions.CreateComment) => action.payload),
    mergeMap((comment: CommentModel) =>
      this.commentService.createComment(comment).pipe(
        map(
          (newComment: CommentModel) =>
            new commentActions.CreateCommentSuccess(newComment)
        ),
        catchError((err) =>
          of(
            new commentActions.CreateCommentFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  updateComment$: Observable<Action> = this.actions$.pipe(
    ofType<commentActions.UpdateComment>(
      commentActions.CommentActionTypes.UPDATE_COMMENT
    ),
    map((action: commentActions.UpdateComment) => action.payload),
    mergeMap((comment: CommentModel) =>
      this.commentService.updateComment(comment).pipe(
        map(
          (updateComment: CommentModel) =>
            new commentActions.UpdateCommentSuccess({
              id: updateComment.id,
              changes: updateComment,
            })
        ),
        catchError((err) =>
          of(
            new commentActions.UpdateCommentFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  deleteComment$: Observable<Action> = this.actions$.pipe(
    ofType<commentActions.DeleteComment>(
      commentActions.CommentActionTypes.DELETE_COMMENT
    ),
    map((action: commentActions.DeleteComment) => action.payload),
    mergeMap((id: string) =>
      this.commentService.deleteComment(id).pipe(
        map(() => new commentActions.DeleteCommentSuccess(id)),
        catchError((err) =>
          of(
            new commentActions.DeleteCommentFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );
}
