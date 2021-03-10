import { Update } from '@ngrx/entity';
import { Action } from '@ngrx/store';

import { AuthorModel } from '../../models/author.model';


export enum AuthorActionTypes {
  LOAD_AUTHORS = '[Author/API Load Authors Request',
  LOAD_AUTHORS_SUCCESS = '[Author/API Load Authors Success',
  LOAD_AUTHORS_FAIL = '[Author/API Load Authors Failure',
  LOAD_AUTHOR = '[Author/API Load Author Request',
  LOAD_AUTHOR_SUCCESS = '[Author/API Load Author Success',
  LOAD_AUTHOR_FAIL = '[Author/API Load Author Failure',
  CREATE_AUTHOR = '[Author/API Create Author Request',
  CREATE_AUTHOR_SUCCESS = '[Author/API Create Author Success',
  CREATE_AUTHOR_FAIL = '[Author/API Create Author Failure',
  UPDATE_AUTHOR = '[Author/API Update Author Request',
  UPDATE_AUTHOR_SUCCESS = '[Author/API Update Author Success',
  UPDATE_AUTHOR_FAIL = '[Author/API Update Author Failure',
  DELETE_AUTHOR = '[Author/API Delete Author Request',
  DELETE_AUTHOR_SUCCESS = '[Author/API Delete Author Success',
  DELETE_AUTHOR_FAIL = '[Author/API Delete Author Failure',
}

export class LoadAuthors implements Action {
  readonly type = AuthorActionTypes.LOAD_AUTHORS;
}

export class LoadAuthorsSuccess implements Action {
  readonly type = AuthorActionTypes.LOAD_AUTHORS_SUCCESS;

  constructor(public payload: AuthorModel[]) {
  }
}

export class LoadAuthorsFail implements Action {
  readonly type = AuthorActionTypes.LOAD_AUTHORS_FAIL;

  constructor(public payload: string) {
  }
}

//
export class LoadAuthor implements Action {
  readonly type = AuthorActionTypes.LOAD_AUTHOR;
  constructor(public payload: string) {}
}

export class LoadAuthorSuccess implements Action {
  readonly type = AuthorActionTypes.LOAD_AUTHOR_SUCCESS;

  constructor(public payload: AuthorModel) {
  }
}

export class LoadAuthorFail implements Action {
  readonly type = AuthorActionTypes.LOAD_AUTHOR_FAIL;

  constructor(public payload: string) {
  }
}

//
export class CreateAuthor implements Action {
  readonly type = AuthorActionTypes.CREATE_AUTHOR;

  constructor(public payload: AuthorModel) {
  }

}

export class CreateAuthorSuccess implements Action {
  readonly type = AuthorActionTypes.CREATE_AUTHOR_SUCCESS;

  constructor(public payload: AuthorModel) {
  }
}

export class CreateAuthorFail implements Action {
  readonly type = AuthorActionTypes.CREATE_AUTHOR_FAIL;

  constructor(public payload: string) {
  }
}

//
export class UpdateAuthor implements Action {
  readonly type = AuthorActionTypes.UPDATE_AUTHOR;

  constructor(public payload: AuthorModel) {
  }

}

export class UpdateAuthorSuccess implements Action {
  readonly type = AuthorActionTypes.UPDATE_AUTHOR_SUCCESS;

  constructor(public payload: Update<AuthorModel>) {
  }
}

export class UpdateAuthorFail implements Action {
  readonly type = AuthorActionTypes.UPDATE_AUTHOR_FAIL;

  constructor(public payload: string) {
  }
}

//
export class DeleteAuthor implements Action {
  readonly type = AuthorActionTypes.DELETE_AUTHOR;

  constructor(public payload: string) {
  }
}

export class DeleteAuthorSuccess implements Action {
  readonly type = AuthorActionTypes.DELETE_AUTHOR_SUCCESS;

  constructor(public payload: string) {
  }
}

export class DeleteAuthorFail implements Action {
  readonly type = AuthorActionTypes.DELETE_AUTHOR_FAIL;

  constructor(public payload: string) {
  }
}

export type Actions =
  | LoadAuthors
  | LoadAuthorsSuccess
  | LoadAuthorsFail
  | LoadAuthor
  | LoadAuthorSuccess
  | LoadAuthorFail
  | CreateAuthor
  | CreateAuthorSuccess
  | CreateAuthorFail
  | UpdateAuthor
  | UpdateAuthorSuccess
  | UpdateAuthorFail
  | DeleteAuthor
  | DeleteAuthorSuccess
  | DeleteAuthorFail;
