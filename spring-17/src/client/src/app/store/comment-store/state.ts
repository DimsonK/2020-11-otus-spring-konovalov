import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity';
import { CommentModel } from '../../models/comment.model';

export interface CommentState extends EntityState<CommentModel> {
  selectedCommentId: string | null;
  loading: boolean;
  loaded: boolean;
  error: string;
}

export const commentAdapter: EntityAdapter<CommentModel> = createEntityAdapter<CommentModel>();

export const initialCommentState: CommentState = commentAdapter.getInitialState(
  {
    ids: [],
    entities: {},
    selectedCommentId: null,
    loading: false,
    loaded: false,
    error: '',
  }
);
