import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NGXLogger } from 'ngx-logger';
import { Subscription } from 'rxjs';
import { GenreModel } from '../../models/genre.model';

@Component({
  selector: 'app-genre-edit',
  templateUrl: './genre-edit.component.html',
  styleUrls: ['./genre-edit.component.css']
})
export class GenreEditComponent implements OnInit, OnDestroy {
  @Input() public display = false;
  @Input() public genre: GenreModel | null = null;
  @Output() public displayChange = new EventEmitter<boolean>();
  @Output() public genreChange = new EventEmitter<GenreModel>();

  public dialogHeader = '';
  public submitted = false;
  private subscriptions: Subscription = new Subscription();

  public genreEditForm: FormGroup = new FormGroup({
    id: new FormControl(''),
    genreName: new FormControl('', Validators.required),
  });

  constructor(
    private logger: NGXLogger
  ) {
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  onDisplayChange(val: boolean) {
    if (this.display) {
      if (this.genre) {
        this.logger.info(`onDisplayChange: ${JSON.stringify(this.genre)}`);

        this.dialogHeader = 'Редактировать жанр';
        this.genreEditForm.patchValue({
          id: this.genre && this.genre.id ? this.genre.id : null,
          genreName: this.genre.name,
        });
      } else {
        this.dialogHeader = 'Добавить жанр';
        this.genreEditForm.patchValue({
          id: null,
          genreName: ''
        });
      }
    }
    this.submitted = false;
    this.display = val;
    this.displayChange.emit(val);
  }

  onGenreChange() {
    this.submitted = true;
    const nameField = this.genreEditForm.get('genreName');
    const updateGenre: GenreModel = {
      id: this.genre ? this.genre.id : '0',
      name: nameField?.value,
    };
    if (this.genreEditForm.valid) {
      this.genre = updateGenre;
      this.genreChange.emit(updateGenre);
      this.display = false;
    }
  }

}
