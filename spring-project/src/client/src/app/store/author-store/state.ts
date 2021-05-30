import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity';
import { AuthorModel } from '../../models/author.model';

export interface AuthorState extends EntityState<AuthorModel> {
  selectedAuthorId: string | null;
  loading: boolean;
  loaded: boolean;
  error: string | null;
}

export const authorAdapter: EntityAdapter<AuthorModel> = createEntityAdapter<AuthorModel>();

export const initialAuthorState: AuthorState = authorAdapter.getInitialState(
  {
    ids: [],
    entities: {},
    selectedAuthorId: null,
    loading: false,
    loaded: false,
    error: null,
  }
);
