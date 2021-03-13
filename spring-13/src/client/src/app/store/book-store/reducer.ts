import * as bookActions from './actions';
import { bookAdapter, BookState, initialBookState } from './state';

export function bookReducer(
  state = initialBookState,
  action: bookActions.Actions
): BookState {
  switch (action.type) {
    case bookActions.BookActionTypes.LOAD_BOOKS_SUCCESS: {
      return bookAdapter.setAll(action.payload, {
        ...state,
        loading: false,
        loaded: true,
      });
    }
    case bookActions.BookActionTypes.LOAD_BOOKS_FAIL: {
      return {
        ...state,
        entities: {},
        loading: false,
        loaded: false,
        error: action.payload,
      };
    }
    case bookActions.BookActionTypes.LOAD_BOOKS_LIKE_NAME_SUCCESS: {
      return bookAdapter.setAll(action.payload, {
        ...state,
        loading: false,
        loaded: true,
      });
    }
    case bookActions.BookActionTypes.LOAD_BOOKS_LIKE_NAME_FAIL: {
      return {
        ...state,
        entities: {},
        loading: false,
        loaded: false,
        error: action.payload,
      };
    }
    case bookActions.BookActionTypes.LOAD_BOOK_SUCCESS: {
      return bookAdapter.addOne(action.payload, {
        ...state,
        selectedBookId: action.payload.id,
      });
    }
    case bookActions.BookActionTypes.LOAD_BOOK_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case bookActions.BookActionTypes.CREATE_BOOK_SUCCESS: {
      return bookAdapter.addOne(action.payload, state);
    }
    case bookActions.BookActionTypes.CREATE_BOOK_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case bookActions.BookActionTypes.UPDATE_BOOK_SUCCESS: {
      return bookAdapter.updateOne(action.payload, state);
    }
    case bookActions.BookActionTypes.UPDATE_BOOK_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }

    case bookActions.BookActionTypes.DELETE_BOOK_SUCCESS: {
      return bookAdapter.removeOne(action.payload, state);
    }
    case bookActions.BookActionTypes.DELETE_BOOK_FAIL: {
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
