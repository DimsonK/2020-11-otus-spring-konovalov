import { Component, OnDestroy, OnInit } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { NGXLogger } from 'ngx-logger';
import { MessageService } from 'primeng/api';
import { Observable, Subscription } from 'rxjs';
import { AuthorModel } from '../../models/author.model';
import { BookModel } from '../../models/book.model';
import { CommentModel } from '../../models/comment.model';
import { GenreModel } from '../../models/genre.model';
import * as authorStore from '../../store/author-store';
import * as bookStore from '../../store/book-store';
import * as commentStore from '../../store/comment-store';
import * as genreStore from '../../store/genre-store';

@Component({
  selector: 'app-books-tab',
  templateUrl: './books-tab.component.html',
  styleUrls: ['./books-tab.component.css']
})
export class BooksTabComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();

  public displayEditBookDialog = false;
  public selectedBook: BookModel | null = null;
  public tmpBook: BookModel | null = null;
  public displayEditCommentDialog = false;
  public selectedComment: CommentModel | null = null;
  public tmpComment: CommentModel | null = null;

  public books: BookModel[] = [];
  public authors: AuthorModel[] = [];
  public genres: GenreModel[] = [];
  public comments: CommentModel[] = [];

  public action: 'ADD' | 'EDIT' | null = null;
  public delAction: 'delBook' | 'delComment' | null = null;

  constructor(
    private bookStore$: Store<bookStore.BookStoreState.BookState>,
    private authorStore$: Store<authorStore.AuthorStoreState.AuthorState>,
    private genreStore$: Store<genreStore.GenreStoreState.GenreState>,
    private commentStore$: Store<commentStore.CommentStoreState.CommentState>,
    private messageService: MessageService,
    private logger: NGXLogger
  ) {
  }

  ngOnInit(): void {
    this.onError(
      this.bookStore$.pipe(
        select(bookStore.BookStoreSelectors.getError)
      )
    );
    this.onError(
      this.authorStore$.pipe(
        select(authorStore.AuthorStoreSelectors.getError)
      )
    );
    this.onError(
      this.genreStore$.pipe(
        select(genreStore.GenreStoreSelectors.getError)
      )
    );
    this.onError(
      this.commentStore$.pipe(
        select(commentStore.CommentStoreSelectors.getError)
      )
    );
    this.subscriptions.add(
      this.bookStore$
        .select(bookStore.BookStoreSelectors.getBooks)
        .subscribe((data) => {
          this.books = data;
        })
    );
    this.subscriptions.add(
      this.authorStore$
        .select(authorStore.AuthorStoreSelectors.getAuthors)
        .subscribe((data) => {
          this.authors = data;
        })
    );
    this.subscriptions.add(
      this.genreStore$
        .select(genreStore.GenreStoreSelectors.getGenres)
        .subscribe((data) => {
          this.genres = data;
        })
    );
    this.subscriptions.add(
      this.commentStore$
        .select(commentStore.CommentStoreSelectors.getComments)
        .subscribe((data) => {
          this.comments = data;
        })
    );
    this.bookStore$.dispatch(
      new bookStore.BookStoreActions.LoadBooks()
    );
    this.authorStore$.dispatch(
      new authorStore.AuthorStoreActions.LoadAuthors()
    );
    this.genreStore$.dispatch(
      new genreStore.GenreStoreActions.LoadGenres()
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  // ------------------------------------------------------------------------------
  addBook() {
    this.action = 'ADD';
    this.tmpBook = null;
    this.displayEditBookDialog = true;
  }

  editBook(book: BookModel) {
    if (!book) {
      this.action = 'ADD';
    } else {
      this.action = 'EDIT';
    }
    this.selectedBook = book;
    this.tmpBook = book;
    this.displayEditBookDialog = true;
  }

  saveBookChanges(book: BookModel) {
    this.logger.info(`saveBookChanges: ${book}`);
    if (this.action === 'ADD') {
      this.bookStore$.dispatch(
        new bookStore.BookStoreActions.CreateBook(book)
      );
    } else {
      this.bookStore$.dispatch(
        new bookStore.BookStoreActions.UpdateBook(book)
      );
    }
  }

  deleteBook(book: BookModel) {
    this.tmpBook = book;
    this.delAction = 'delBook';
    this.messageService.clear();
    this.messageService.add({
      key: 'delConfirmDialog',
      sticky: true,
      severity: 'warn',
      summary: 'Вы уверены?',
      detail: 'Подтвердите удаление книги',
    });
  }

  // ------------------------------------------------------------------------------
  addComment() {
    this.action = 'ADD';
    this.tmpComment = null;
    this.displayEditCommentDialog = true;
  }

  editComment(comment: CommentModel) {
    if (!comment) {
      this.action = 'ADD';
    } else {
      this.action = 'EDIT';
    }
    this.selectedComment = comment;
    this.tmpComment = comment;
    this.displayEditCommentDialog = true;
  }

  saveCommentChanges(comment: CommentModel) {
    this.logger.info(`saveCommentChanges: ${comment}`);
    if (this.action === 'ADD') {
      this.commentStore$.dispatch(
        new commentStore.CommentStoreActions.CreateComment(comment)
      );
    } else {
      this.commentStore$.dispatch(
        new commentStore.CommentStoreActions.UpdateComment(comment)
      );
    }
  }

  deleteComment(comment: CommentModel) {
    this.tmpComment = comment;
    this.delAction = 'delComment';
    this.messageService.clear();
    this.messageService.add({
      key: 'delConfirmDialog',
      sticky: true,
      severity: 'warn',
      summary: 'Вы уверены?',
      detail: 'Подтвердите удаление комментария',
    });
  }


  // ------------------------------------------------------------------------------
  // Confirmation
  onConfirm(action: string | null) {
    switch (action) {
      case 'delBook': {
        if (this.tmpBook) {
          this.bookStore$.dispatch(
            new bookStore.BookStoreActions.DeleteBook(this.tmpBook.id)
          );
        }
        this.selectedBook = null;
        this.selectedComment = null;
        this.tmpBook = null;
        this.comments = [];
        this.messageService.clear('delConfirmDialog');
        break;
      }
      case 'delComment': {
        if (this.tmpComment) {
          this.commentStore$.dispatch(
            new commentStore.CommentStoreActions.DeleteComment(this.tmpComment.id)
          );
        }
        this.messageService.clear('delConfirmDialog');
        break;
      }
      default: {
        this.messageService.clear('delConfirmDialog');
        break;
      }
    }
  }

  onReject() {
    this.messageService.clear('delConfirmDialog');
  }

  onBookRowSelect(event: any) {
    this.commentStore$.dispatch(
      new commentStore.CommentStoreActions.LoadComments(event.data.id)
    );

  }

  onError(errorFlow$: Observable<any>) {
    this.subscriptions.add(
      errorFlow$.subscribe((err) => {
        if (err && err.length > 0) {
          this.messageService.add({
            key: 'serviceError',
            severity: 'error',
            summary: 'Ошибка сервиса',
            detail: err.toString(),
            closable: true,
          });
        }
      })
    );
  }
}
