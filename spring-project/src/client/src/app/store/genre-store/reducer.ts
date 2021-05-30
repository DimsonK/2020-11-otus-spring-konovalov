import * as genreActions from './actions';
import { GenreState, genreAdapter, initialGenreState } from './state';

export function genreReducer(
  state = initialGenreState,
  action: genreActions.Actions
): GenreState {
  switch (action.type) {
    case genreActions.GenreActionTypes.LOAD_GENRES_SUCCESS: {
      return genreAdapter.setAll(action.payload, {
        ...state,
        loading: false,
        loaded: true,
      });
    }
    case genreActions.GenreActionTypes.LOAD_GENRES_FAIL: {
      return {
        ...state,
        entities: {},
        loading: false,
        loaded: false,
        error: action.payload,
      };
    }
    case genreActions.GenreActionTypes.LOAD_GENRE_SUCCESS: {
      return genreAdapter.addOne(action.payload, {
        ...state,
        selectedGenreId: action.payload.id,
      });
    }
    case genreActions.GenreActionTypes.LOAD_GENRE_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case genreActions.GenreActionTypes.CREATE_GENRE_SUCCESS: {
      return genreAdapter.addOne(action.payload, state);
    }
    case genreActions.GenreActionTypes.CREATE_GENRE_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case genreActions.GenreActionTypes.UPDATE_GENRE_SUCCESS: {
      return genreAdapter.updateOne(action.payload, state);
    }
    case genreActions.GenreActionTypes.UPDATE_GENRE_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }

    case genreActions.GenreActionTypes.DELETE_GENRE_SUCCESS: {
      return genreAdapter.removeOne(action.payload, state);
    }
    case genreActions.GenreActionTypes.DELETE_GENRE_FAIL: {
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
