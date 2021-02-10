import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { select, Store } from '@ngrx/store';
import { NGXLogger } from 'ngx-logger';
import { MessageService } from 'primeng/api';
import { Observable, Subscription } from 'rxjs';
import { BookModel } from '../../models/book.model';
import { CommentModel } from '../../models/comment.model';
import * as bookStore from '../../store/book-store';
import * as commentStore from '../../store/comment-store';

@Component({
  selector: 'app-books-view',
  templateUrl: './books-view.component.html',
  styleUrls: ['./books-view.component.css']
})
export class BooksViewComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();

  public searchText = '';

  public books: BookModel[] = [];
  public comments: CommentModel[] = [];

  constructor(
    private bookStore$: Store<bookStore.BookStoreState.BookState>,
    private commentStore$: Store<commentStore.CommentStoreState.CommentState>,
    private messageService: MessageService,
    private logger: NGXLogger,
    public route: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.onError(
      this.bookStore$.pipe(
        select(bookStore.BookStoreSelectors.getError)
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
      this.commentStore$
        .select(commentStore.CommentStoreSelectors.getComments)
        .subscribe((data) => {
          this.comments = data;
        })
    );
    this.subscriptions.add(
      this.route.queryParams.subscribe((params) => {
        this.searchText = params.searchText;
        if (this.searchText && this.searchText != '') {
          this.logger.info(`searchText: ${this.searchText}`);
          this.bookStore$.dispatch(
            new bookStore.BookStoreActions.LoadBooksLikeName(this.searchText)
          );
        } else {
          this.logger.info(`searchAll`);
          this.bookStore$.dispatch(
            new bookStore.BookStoreActions.LoadBooks()
          );
        }
      })
    );
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
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
