import { createSelector, createFeatureSelector } from '@ngrx/store';

import { BookState, bookAdapter } from './state';

const getBookFeatureState = createFeatureSelector<BookState>('books');

export const getBooks = createSelector(
  getBookFeatureState,
  bookAdapter.getSelectors().selectAll
);

export const getBooksLoading = createSelector(
  getBookFeatureState,
  (state: BookState) => state.loading
);

export const getBooksLoaded = createSelector(
  getBookFeatureState,
  (state: BookState) => state.loaded
);

export const getError = createSelector(
  getBookFeatureState,
  (state: BookState) => state.error
);

export const getCurrentBookId = createSelector(
  getBookFeatureState,
  (state: BookState) => state.selectedBookId
);
export const getCurrentBook = createSelector(
  getBookFeatureState,
  getCurrentBookId,
  (state) => {
    if (state !== null && state.selectedBookId !== null) {
      return state.entities[state.selectedBookId]
    }
    return null;
  }
);
