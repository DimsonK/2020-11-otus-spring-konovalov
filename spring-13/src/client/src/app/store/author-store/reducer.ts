import * as authorActions from './actions';
import { AuthorState, authorAdapter, initialAuthorState } from './state';

export function authorReducer(
  state = initialAuthorState,
  action: authorActions.Actions
): AuthorState {
  switch (action.type) {
    case authorActions.AuthorActionTypes.LOAD_AUTHORS_SUCCESS: {
      return authorAdapter.setAll(action.payload, {
        ...state,
        loading: false,
        loaded: true,
      });
    }
    case authorActions.AuthorActionTypes.LOAD_AUTHORS_FAIL: {
      return {
        ...state,
        entities: {},
        loading: false,
        loaded: false,
        error: action.payload,
      };
    }
    case authorActions.AuthorActionTypes.LOAD_AUTHOR_SUCCESS: {
      return authorAdapter.addOne(action.payload, {
        ...state,
        selectedAuthorId: action.payload.id,
      });
    }
    case authorActions.AuthorActionTypes.LOAD_AUTHOR_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case authorActions.AuthorActionTypes.CREATE_AUTHOR_SUCCESS: {
      return authorAdapter.addOne(action.payload, state);
    }
    case authorActions.AuthorActionTypes.CREATE_AUTHOR_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case authorActions.AuthorActionTypes.UPDATE_AUTHOR_SUCCESS: {
      return authorAdapter.updateOne(action.payload, state);
    }
    case authorActions.AuthorActionTypes.UPDATE_AUTHOR_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }

    case authorActions.AuthorActionTypes.DELETE_AUTHOR_SUCCESS: {
      return authorAdapter.removeOne(action.payload, state);
    }
    case authorActions.AuthorActionTypes.DELETE_AUTHOR_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    default: {
      return state;
    }
  }
}
