import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';
import { Observable, of, of as observableOf } from 'rxjs';
import { catchError, map, mergeMap, switchMap } from 'rxjs/operators';
import { UserModel } from '../../models/user.model';
import { CurrentUserService } from '../../services/current-user.service';
import * as currentUserActions from './actions';

@Injectable()
export class CurrentUserStoreEffects {
  constructor(
    private currentUserService: CurrentUserService,
    private actions$: Actions
  ) {
  }

  @Effect()
  loadCurrentUser$: Observable<Action> = this.actions$.pipe(
    ofType<currentUserActions.LoadCurrentUser>(
      currentUserActions.CurrentUserActionTypes.LOAD_CURRENT_USER
    ),
    switchMap((action: currentUserActions.LoadCurrentUser) =>
      this.currentUserService.loadCurrentUser().pipe(
        map((user: UserModel) => new currentUserActions.LoadCurrentUserSuccess(user)),
        catchError((error) =>
          observableOf(new currentUserActions.LoadCurrentUserFail(error))
        )
      )
    )
  );

  @Effect()
  updateCurrentUser$: Observable<Action> = this.actions$.pipe(
    ofType<currentUserActions.UpdateCurrentUser>(
      currentUserActions.CurrentUserActionTypes.UPDATE_CURRENT_USER
    ),
    map((action: currentUserActions.UpdateCurrentUser) => action.payload),
    mergeMap((currentUser: UserModel) =>
      this.currentUserService.updateCurrentUser(currentUser).pipe(
        map(
          (updateCurrentUser: UserModel) =>
            new currentUserActions.UpdateCurrentUserSuccess({
              id: updateCurrentUser.id,
              changes: updateCurrentUser,
            })
        ),
        catchError((err) =>
          of(
            new currentUserActions.UpdateCurrentUserFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

  @Effect()
  deleteCurrentUser$: Observable<Action> = this.actions$.pipe(
    ofType<currentUserActions.DeleteCurrentUser>(
      currentUserActions.CurrentUserActionTypes.DELETE_CURRENT_USER
    ),
    map((action: currentUserActions.DeleteCurrentUser) => action.payload),
    mergeMap((userName: string) =>
      this.currentUserService.deleteCurrentUser(userName).pipe(
        map(
          (name: string) =>
            new currentUserActions.DeleteCurrentUserSuccess(name)
        ),
        catchError((err) =>
          of(
            new currentUserActions.DeleteCurrentUserFail(
              `${new Date()}\n${JSON.stringify(err)}`
            )
          )
        )
      )
    )
  );

}
