import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity';
import { GenreModel } from '../../models/genre.model';

export interface GenreState extends EntityState<GenreModel> {
  selectedGenreId: string | null;
  loading: boolean;
  loaded: boolean;
  error: string | null;
}

export const genreAdapter: EntityAdapter<GenreModel> = createEntityAdapter<GenreModel>();

export const initialGenreState: GenreState = genreAdapter.getInitialState(
  {
    ids: [],
    entities: {},
    selectedGenreId: null,
    loading: false,
    loaded: false,
    error: null,
  }
);
