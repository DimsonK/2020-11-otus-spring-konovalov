import { Component, OnDestroy, OnInit } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { NGXLogger } from 'ngx-logger';
import { MessageService } from 'primeng/api';
import { Observable, Subscription } from 'rxjs';
import { GenreModel } from '../../models/genre.model';
import * as genreStore from '../../store/genre-store';

@Component({
  selector: 'app-genres-tab',
  templateUrl: './genres-tab.component.html',
  styleUrls: ['./genres-tab.component.css']
})
export class GenresTabComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();

  public displayEditGenreDialog = false;
  public selectedGenre: GenreModel | null = null;
  public tmpGenre: GenreModel | null = null;

  public genres: GenreModel[] = [];

  public action: 'ADD' | 'EDIT' | null = null;
  public delAction: 'delGenre' | null = null;

  constructor(
    private genreStore$: Store<genreStore.GenreStoreState.GenreState>,
    private messageService: MessageService,
    private logger: NGXLogger,
  ) {
  }

  ngOnInit(): void {
    this.onError(
      this.genreStore$.pipe(
        select(genreStore.GenreStoreSelectors.getError)
      )
    );
    this.subscriptions.add(
      this.genreStore$
        .select(genreStore.GenreStoreSelectors.getGenres)
        .subscribe((data) => {
          this.genres = data;
        })
    );
    this.genreStore$.dispatch(
      new genreStore.GenreStoreActions.LoadGenres()
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  // ------------------------------------------------------------------------------
  addGenre() {
    this.action = 'ADD';
    this.tmpGenre = null;
    this.displayEditGenreDialog = true;
  }

  editGenre(genre: GenreModel) {
    if (!genre) {
      this.action = 'ADD';
    } else {
      this.action = 'EDIT';
    }
    this.selectedGenre = genre;
    this.tmpGenre = genre;
    this.displayEditGenreDialog = true;
  }

  saveGenreChanges(genre: GenreModel) {
    this.logger.info(`saveGenreChanges: ${genre}`);
    if (this.action === 'ADD') {
      this.genreStore$.dispatch(
        new genreStore.GenreStoreActions.CreateGenre(genre)
      );
    } else {
      this.genreStore$.dispatch(
        new genreStore.GenreStoreActions.UpdateGenre(genre)
      );
    }
  }

  deleteGenre(genre: GenreModel) {
    this.tmpGenre = genre;
    this.delAction = 'delGenre';
    this.messageService.clear();
    this.messageService.add({
      key: 'delConfirmDialog',
      sticky: true,
      severity: 'warn',
      summary: 'Вы уверены?',
      detail: 'Подтвердите удаление жанра',
    });
  }

  // ------------------------------------------------------------------------------
  // Confirmation
  onConfirm(action: string | null) {
    if (this.tmpGenre) {
      this.genreStore$.dispatch(
        new genreStore.GenreStoreActions.DeleteGenre(this.tmpGenre.id)
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
