import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity';
import { UserModel } from '../../models/user.model';

export interface CurrentUserState extends EntityState<UserModel> {
  selectedCurrentUserId: string | null;
  loading: boolean;
  loaded: boolean;
  error: string | null;
}

export const currentUserAdapter: EntityAdapter<UserModel> = createEntityAdapter<UserModel>();

export const initialCurrentUserState: CurrentUserState = currentUserAdapter.getInitialState(
  {
    ids: [],
    entities: {},
    selectedCurrentUserId: null,
    loading: false,
    loaded: false,
    error: null,
  }
);
