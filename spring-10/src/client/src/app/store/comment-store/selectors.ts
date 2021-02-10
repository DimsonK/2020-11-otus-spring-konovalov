import { createFeatureSelector, createSelector } from '@ngrx/store';

import { commentAdapter, CommentState } from './state';

const getCommentFeatureState = createFeatureSelector<CommentState>('comments');

export const getComments = createSelector(
  getCommentFeatureState,
  commentAdapter.getSelectors().selectAll
);

export const getCommentsLoading = createSelector(
  getCommentFeatureState,
  (state: CommentState) => state.loading
);

export const getCommentsLoaded = createSelector(
  getCommentFeatureState,
  (state: CommentState) => state.loaded
);

export const getError = createSelector(
  getCommentFeatureState,
  (state: CommentState) => state.error
);

export const getCurrentCommentId = createSelector(
  getCommentFeatureState,
  (state: CommentState) => state.selectedCommentId
);
export const getCurrentComment = createSelector(
  getCommentFeatureState,
  getCurrentCommentId,
  (state) => {
    if (state !== null && state.selectedCommentId !== null) {
      return state.entities[state.selectedCommentId];
    }
    return null;
  }
);
