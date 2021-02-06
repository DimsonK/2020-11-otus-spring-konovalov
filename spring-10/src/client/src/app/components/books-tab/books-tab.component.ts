import { Component, OnDestroy, OnInit } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { MessageService } from 'primeng/api';
import { Observable, Subscription } from 'rxjs';
import { AuthorModel } from '../../models/author.model';
import { BookModel } from '../../models/book.model';
import { GenreModel } from '../../models/genre.model';
import * as authorStore from '../../store/author-store';

import * as bookStore from '../../store/book-store';
import * as genreStore from '../../store/genre-store';

@Component({
  selector: 'app-books-tab',
  templateUrl: './books-tab.component.html',
  styleUrls: ['./books-tab.component.css']
})
export class BooksTabComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();
  private selBook: BookModel | null = null;

  public displayEditBookDialog = false;
  public tmpBook: BookModel = new BookModel();

  public books: BookModel[] = [];
  public authors: AuthorModel[] = [];
  public genres: GenreModel[] = [];

  public action: 'ADD' | 'EDIT' | null = null;
  public delAction: 'delBook' | null = null;

  constructor(
    private bookStore$: Store<bookStore.BookStoreState.BookState>,
    private authorStore$: Store<authorStore.AuthorStoreState.AuthorState>,
    private genreStore$: Store<genreStore.GenreStoreState.GenreState>,
    private messageService: MessageService
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
    this.tmpBook = new BookModel();
    this.displayEditBookDialog = true;
  }

  editBook(book: BookModel) {
    if (!book) {
      this.action = 'ADD';
    } else {
      this.action = 'EDIT';
    }
    this.selBook = book;
    this.tmpBook = book;
    this.displayEditBookDialog = true;
  }

  saveBookChanges(book: BookModel) {
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
      detail: 'Подтвердите удаление прошивки',
    });
  }

  // ------------------------------------------------------------------------------
  // Confirmation
  onConfirm(action: string | null) {
    if (this.tmpBook) {
      this.bookStore$.dispatch(
        new bookStore.BookStoreActions.DeleteBook(this.tmpBook.id)
      );
    }
    this.messageService.clear('delConfirmDialog');
  }

  onReject() {
    this.messageService.clear('delConfirmDialog');
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
          });
        }
      })
    );
  }
}
