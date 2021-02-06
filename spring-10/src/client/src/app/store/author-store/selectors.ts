import { createFeatureSelector, createSelector } from '@ngrx/store';

import { authorAdapter, AuthorState } from './state';

const getAuthorFeatureState = createFeatureSelector<AuthorState>('authors');

export const getAuthors = createSelector(
  getAuthorFeatureState,
  authorAdapter.getSelectors().selectAll
);

export const getAuthorsLoading = createSelector(
  getAuthorFeatureState,
  (state: AuthorState) => state.loading
);

export const getAuthorsLoaded = createSelector(
  getAuthorFeatureState,
  (state: AuthorState) => state.loaded
);

export const getError = createSelector(
  getAuthorFeatureState,
  (state: AuthorState) => state.error
);

export const getCurrentAuthorId = createSelector(
  getAuthorFeatureState,
  (state: AuthorState) => state.selectedAuthorId
);
// export const getCurrentAuthor = createSelector(
//   getAuthorFeatureState,
//   getCurrentAuthorId,
//   (state) => state.entities[state.selectedAuthorId]
// );
