import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity';
import { BookModel } from '../../models/book.model';

export interface BookState extends EntityState<BookModel> {
  selectedBookId: string | null;
  loading: boolean;
  loaded: boolean;
  error: string | null;
}

export const bookAdapter: EntityAdapter<BookModel> = createEntityAdapter<BookModel>();

export const initialBookState: BookState = bookAdapter.getInitialState(
  {
    ids: [],
    entities: {},
    selectedBookId: null,
    loading: false,
    loaded: false,
    error: null,
  }
);
