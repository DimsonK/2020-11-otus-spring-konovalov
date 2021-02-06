import { Update } from '@ngrx/entity';
import { Action } from '@ngrx/store';

import { CommentModel } from '../../models/comment.model';


export enum CommentActionTypes {
  LOAD_COMMENTS = '[Comment/API Load Comments Request',
  LOAD_COMMENTS_SUCCESS = '[Comment/API Load Comments Success',
  LOAD_COMMENTS_FAIL = '[Comment/API Load Comments Failure',
  LOAD_COMMENT = '[Comment/API Load Comment Request',
  LOAD_COMMENT_SUCCESS = '[Comment/API Load Comment Success',
  LOAD_COMMENT_FAIL = '[Comment/API Load Comment Failure',
  CREATE_COMMENT = '[Comment/API Create Comment Request',
  CREATE_COMMENT_SUCCESS = '[Comment/API Create Comment Success',
  CREATE_COMMENT_FAIL = '[Comment/API Create Comment Failure',
  UPDATE_COMMENT = '[Comment/API Update Comment Request',
  UPDATE_COMMENT_SUCCESS = '[Comment/API Update Comment Success',
  UPDATE_COMMENT_FAIL = '[Comment/API Update Comment Failure',
  DELETE_COMMENT = '[Comment/API Delete Comment Request',
  DELETE_COMMENT_SUCCESS = '[Comment/API Delete Comment Success',
  DELETE_COMMENT_FAIL = '[Comment/API Delete Comment Failure',
}

export class LoadComments implements Action {
  readonly type = CommentActionTypes.LOAD_COMMENTS;
}

export class LoadCommentsSuccess implements Action {
  readonly type = CommentActionTypes.LOAD_COMMENTS_SUCCESS;

  constructor(public payload: CommentModel[]) {
  }
}

export class LoadCommentsFail implements Action {
  readonly type = CommentActionTypes.LOAD_COMMENTS_FAIL;

  constructor(public payload: string) {
  }
}

//
export class LoadComment implements Action {
  readonly type = CommentActionTypes.LOAD_COMMENT;

  constructor(public payload: string) {
  }
}

export class LoadCommentSuccess implements Action {
  readonly type = CommentActionTypes.LOAD_COMMENT_SUCCESS;

  constructor(public payload: CommentModel) {
  }
}

export class LoadCommentFail implements Action {
  readonly type = CommentActionTypes.LOAD_COMMENT_FAIL;

  constructor(public payload: string) {
  }
}

//
export class CreateComment implements Action {
  readonly type = CommentActionTypes.CREATE_COMMENT;

  constructor(public payload: CommentModel) {
  }
}

export class CreateCommentSuccess implements Action {
  readonly type = CommentActionTypes.CREATE_COMMENT_SUCCESS;

  constructor(public payload: CommentModel) {
  }
}

export class CreateCommentFail implements Action {
  readonly type = CommentActionTypes.CREATE_COMMENT_FAIL;

  constructor(public payload: string) {
  }
}

//
export class UpdateComment implements Action {
  readonly type = CommentActionTypes.UPDATE_COMMENT;

  constructor(public payload: CommentModel) {
  }
}

export class UpdateCommentSuccess implements Action {
  readonly type = CommentActionTypes.UPDATE_COMMENT_SUCCESS;

  constructor(public payload: Update<CommentModel>) {
  }
}

export class UpdateCommentFail implements Action {
  readonly type = CommentActionTypes.UPDATE_COMMENT_FAIL;

  constructor(public payload: string) {
  }
}

//
export class DeleteComment implements Action {
  readonly type = CommentActionTypes.DELETE_COMMENT;

  constructor(public payload: string) {
  }
}

export class DeleteCommentSuccess implements Action {
  readonly type = CommentActionTypes.DELETE_COMMENT_SUCCESS;

  constructor(public payload: string) {
  }
}

export class DeleteCommentFail implements Action {
  readonly type = CommentActionTypes.DELETE_COMMENT_FAIL;

  constructor(public payload: string) {
  }
}

export type Actions =
  | LoadComments
  | LoadCommentsSuccess
  | LoadCommentsFail
  | LoadComment
  | LoadCommentSuccess
  | LoadCommentFail
  | CreateComment
  | CreateCommentSuccess
  | CreateCommentFail
  | UpdateComment
  | UpdateCommentSuccess
  | UpdateCommentFail
  | DeleteComment
  | DeleteCommentSuccess
  | DeleteCommentFail;
