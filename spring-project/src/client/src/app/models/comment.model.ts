export class CommentModel {
  public id!: string;
  public postDate!: string;
  public authorName!: string;
  public content!: string;
  public favorite: boolean = false;
  public bookId!: string;
}
