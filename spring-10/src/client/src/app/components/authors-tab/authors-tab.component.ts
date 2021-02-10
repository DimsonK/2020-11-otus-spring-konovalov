import { Component, OnDestroy, OnInit } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { NGXLogger } from 'ngx-logger';
import { MessageService } from 'primeng/api';
import { Observable, Subscription } from 'rxjs';
import { AuthorModel } from '../../models/author.model';
import * as authorStore from '../../store/author-store';

@Component({
  selector: 'app-authors-tab',
  templateUrl: './authors-tab.component.html',
  styleUrls: ['./authors-tab.component.css']
})
export class AuthorsTabComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();

  public displayEditAuthorDialog = false;
  public selectedAuthor: AuthorModel | null = null;
  public tmpAuthor: AuthorModel | null = null;

  public authors: AuthorModel[] = [];

  public action: 'ADD' | 'EDIT' | null = null;
  public delAction: 'delAuthor' | null = null;

  constructor(
    private authorStore$: Store<authorStore.AuthorStoreState.AuthorState>,
    private messageService: MessageService,
    private logger: NGXLogger,
  ) {
  }

  ngOnInit(): void {
    this.onError(
      this.authorStore$.pipe(
        select(authorStore.AuthorStoreSelectors.getError)
      )
    );
    this.subscriptions.add(
      this.authorStore$
        .select(authorStore.AuthorStoreSelectors.getAuthors)
        .subscribe((data) => {
          this.authors = data;
        })
    );
    this.authorStore$.dispatch(
      new authorStore.AuthorStoreActions.LoadAuthors()
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  // ------------------------------------------------------------------------------
  addAuthor() {
    this.action = 'ADD';
    this.tmpAuthor = null;
    this.displayEditAuthorDialog = true;
  }

  editAuthor(author: AuthorModel) {
    if (!author) {
      this.action = 'ADD';
    } else {
      this.action = 'EDIT';
    }
    this.selectedAuthor = author;
    this.tmpAuthor = author;
    this.displayEditAuthorDialog = true;
  }

  saveAuthorChanges(author: AuthorModel) {
    this.logger.info(`saveAuthorChanges: ${author}`);
    if (this.action === 'ADD') {
      this.authorStore$.dispatch(
        new authorStore.AuthorStoreActions.CreateAuthor(author)
      );
    } else {
      this.authorStore$.dispatch(
        new authorStore.AuthorStoreActions.UpdateAuthor(author)
      );
    }
  }

  deleteAuthor(author: AuthorModel) {
    this.tmpAuthor = author;
    this.delAction = 'delAuthor';
    this.messageService.clear();
    this.messageService.add({
      key: 'delConfirmDialog',
      sticky: true,
      severity: 'warn',
      summary: 'Вы уверены?',
      detail: 'Подтвердите удаление автора',
    });
  }

  // ------------------------------------------------------------------------------
  // Confirmation
  onConfirm(action: string | null) {
    if (this.tmpAuthor) {
      this.authorStore$.dispatch(
        new authorStore.AuthorStoreActions.DeleteAuthor(this.tmpAuthor.id)
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
            closable: true,
          });
        }
      })
    );
  }
}
