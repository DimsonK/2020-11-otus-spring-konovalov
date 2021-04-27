import { AuthorStoreState } from './author-store';
import { BookStoreState } from './book-store';
import { CommentStoreState } from './comment-store';
import { GenreStoreState } from './genre-store';

export interface State {
  author: AuthorStoreState.AuthorState;
  book: BookStoreState.BookState;
  comment: CommentStoreState.CommentState;
  genre: GenreStoreState.GenreState;
}
