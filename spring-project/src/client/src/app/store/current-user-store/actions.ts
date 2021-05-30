import { Update } from '@ngrx/entity';
import { Action } from '@ngrx/store';
import { UserModel } from '../../models/user.model';

export enum CurrentUserActionTypes {
  LOAD_CURRENT_USER = '[CurrentUser/API] Load CurrentUser Request',
  LOAD_CURRENT_USER_SUCCESS = '[CurrentUser/API] Load CurrentUser Success',
  LOAD_CURRENT_USER_FAIL = '[CurrentUser/API] Load CurrentUser Failure',
  UPDATE_CURRENT_USER = '[CurrentUser/API] Update CurrentUser Request',
  UPDATE_CURRENT_USER_SUCCESS = '[CurrentUser/API] Update CurrentUser Success',
  UPDATE_CURRENT_USER_FAIL = '[CurrentUser/API] Update CurrentUser Failure',
  DELETE_CURRENT_USER = '[CurrentUser/API] Remove CurrentUser Request',
  DELETE_CURRENT_USER_SUCCESS = '[CurrentUser/API] Remove CurrentUser Success',
  DELETE_CURRENT_USER_FAIL = '[CurrentUser/API] Remove CurrentUser Failure',
}

export class LoadCurrentUser implements Action {
  readonly type = CurrentUserActionTypes.LOAD_CURRENT_USER;
}

export class LoadCurrentUserSuccess implements Action {
  readonly type = CurrentUserActionTypes.LOAD_CURRENT_USER_SUCCESS;

  constructor(public payload: UserModel) {
  }
}

export class LoadCurrentUserFail implements Action {
  readonly type = CurrentUserActionTypes.LOAD_CURRENT_USER_FAIL;

  constructor(public payload: string) {
  }
}

// -----------------------------------------------------
export class UpdateCurrentUser implements Action {
  readonly type = CurrentUserActionTypes.UPDATE_CURRENT_USER;

  constructor(public payload: UserModel) {
  }
}

export class UpdateCurrentUserSuccess implements Action {
  readonly type = CurrentUserActionTypes.UPDATE_CURRENT_USER_SUCCESS;

  constructor(public payload: Update<UserModel>) {
  }
}

export class UpdateCurrentUserFail implements Action {
  readonly type = CurrentUserActionTypes.UPDATE_CURRENT_USER_FAIL;

  constructor(public payload: string) {
  }
}

// -----------------------------------------------------
export class DeleteCurrentUser implements Action {
  readonly type = CurrentUserActionTypes.DELETE_CURRENT_USER;

  constructor(public payload: string) {
  }
}

export class DeleteCurrentUserSuccess implements Action {
  readonly type = CurrentUserActionTypes.DELETE_CURRENT_USER_SUCCESS;

  constructor(public payload: string) {
  }
}

export class DeleteCurrentUserFail implements Action {
  readonly type = CurrentUserActionTypes.DELETE_CURRENT_USER_FAIL;

  constructor(public payload: string) {
  }
}


export type Actions =
  | LoadCurrentUser
  | LoadCurrentUserSuccess
  | LoadCurrentUserFail
  | UpdateCurrentUser
  | UpdateCurrentUserSuccess
  | UpdateCurrentUserFail
  | DeleteCurrentUser
  | DeleteCurrentUserSuccess
  | DeleteCurrentUserFail;
