import { createSelector, createFeatureSelector } from '@ngrx/store';

import { GenreState, genreAdapter } from './state';

const getGenreFeatureState = createFeatureSelector<GenreState>('genres');

export const getGenres = createSelector(
  getGenreFeatureState,
  genreAdapter.getSelectors().selectAll
);

export const getGenresLoading = createSelector(
  getGenreFeatureState,
  (state: GenreState) => state.loading
);

export const getGenresLoaded = createSelector(
  getGenreFeatureState,
  (state: GenreState) => state.loaded
);

export const getError = createSelector(
  getGenreFeatureState,
  (state: GenreState) => state.error
);

export const getCurrentGenreId = createSelector(
  getGenreFeatureState,
  (state: GenreState) => state.selectedGenreId
);
export const getCurrentGenre = createSelector(
  getGenreFeatureState,
  getCurrentGenreId,
  (state) => {
    if (state !== null && state.selectedGenreId !== null) {
      return state.entities[state.selectedGenreId]
    }
    return null;
  }
);
