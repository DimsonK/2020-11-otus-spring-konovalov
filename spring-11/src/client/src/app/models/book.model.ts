import { AuthorModel } from './author.model';
import { CommentModel } from './comment.model';
import { GenreModel } from './genre.model';

export class BookModel {
  public id!: string;
  public name!: string;
  public author?: AuthorModel;
  public genres?: GenreModel[];
  public comments?: CommentModel[];
}
