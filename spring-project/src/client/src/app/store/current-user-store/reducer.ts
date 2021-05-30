import * as currentUserActions from './actions';
import { currentUserAdapter, CurrentUserState, initialCurrentUserState, } from './state';

export function featureReducer(
  state: CurrentUserState = initialCurrentUserState,
  action: currentUserActions.Actions
): CurrentUserState {
  switch (action.type) {
    case currentUserActions.CurrentUserActionTypes.LOAD_CURRENT_USER_SUCCESS: {
      return currentUserAdapter.addOne(action.payload, {
        ...state,
        selectedCurrentUserId: action.payload.id,
        error: null,
        isLoading: true,
      });
    }
    case currentUserActions.CurrentUserActionTypes.LOAD_CURRENT_USER_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    // ------------------------------
    case currentUserActions.CurrentUserActionTypes
      .UPDATE_CURRENT_USER_SUCCESS: {
      return currentUserAdapter.updateOne(action.payload, state);
    }
    case currentUserActions.CurrentUserActionTypes.UPDATE_CURRENT_USER_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    // ------------------------------
    case currentUserActions.CurrentUserActionTypes
      .DELETE_CURRENT_USER_SUCCESS: {
      return currentUserAdapter.removeOne(action.payload, state);
    }
    case currentUserActions.CurrentUserActionTypes.DELETE_CURRENT_USER_FAIL: {
      return {
        ...state,
        error: action.payload,
        loading: false,
      };
    }
    default: {
      return state;
    }
  }
}
