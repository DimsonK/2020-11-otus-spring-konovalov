import * as commentActions from './actions';
import { CommentState, commentAdapter, initialCommentState } from './state';

export function commentReducer(
  state = initialCommentState,
  action: commentActions.Actions
): CommentState {
  switch (action.type) {
    case commentActions.CommentActionTypes.LOAD_COMMENTS_BY_BOOK_ID_SUCCESS: {
      return commentAdapter.setAll(action.payload, {
        ...state,
        loading: false,
        loaded: true,
      });
    }
    case commentActions.CommentActionTypes.LOAD_COMMENTS_BY_BOOK_ID_FAIL: {
      return {
        ...state,
        entities: {},
        loading: false,
        loaded: false,
        error: action.payload,
      };
    }
    case commentActions.CommentActionTypes.LOAD_COMMENT_SUCCESS: {
      return commentAdapter.addOne(action.payload, {
        ...state,
        selectedCommentId: action.payload.id,
      });
    }
    case commentActions.CommentActionTypes.LOAD_COMMENT_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case commentActions.CommentActionTypes.CREATE_COMMENT_SUCCESS: {
      return commentAdapter.addOne(action.payload, state);
    }
    case commentActions.CommentActionTypes.CREATE_COMMENT_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }
    case commentActions.CommentActionTypes.UPDATE_COMMENT_SUCCESS: {
      return commentAdapter.updateOne(action.payload, state);
    }
    case commentActions.CommentActionTypes.UPDATE_COMMENT_FAIL: {
      return {
        ...state,
        error: action.payload,
      };
    }

    case commentActions.CommentActionTypes.DELETE_COMMENT_SUCCESS: {
      return commentAdapter.removeOne(action.payload, state);
    }
    case commentActions.CommentActionTypes.DELETE_COMMENT_FAIL: {
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
