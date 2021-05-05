import { createFeatureSelector, createSelector } from '@ngrx/store';

import { currentUserAdapter, CurrentUserState } from './state';

const getCurrentUserFeatureState = createFeatureSelector<CurrentUserState>(
  'current-user'
);

export const getCurrentUsers = createSelector(
  getCurrentUserFeatureState,
  currentUserAdapter.getSelectors().selectAll
);

export const getCurrentUserLoading = createSelector(
  getCurrentUserFeatureState,
  (state: CurrentUserState) => state.loading
);

export const getCurrentUserLoaded = createSelector(
  getCurrentUserFeatureState,
  (state: CurrentUserState) => state.loaded
);

export const getCurrentUserError = createSelector(
  getCurrentUserFeatureState,
  (state: CurrentUserState) => state.error
);

export const getCurrentUserId = createSelector(
  getCurrentUserFeatureState,
  (state: CurrentUserState) => state.selectedCurrentUserId
);

export const getCurrentUser = createSelector(
  getCurrentUserFeatureState,
  getCurrentUserId,
  (state: CurrentUserState) => {
    if (state !== null && state.selectedCurrentUserId !== null) {
      return state.entities[state.selectedCurrentUserId];
    }
    return null;
  }
);
