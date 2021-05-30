import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NGXLogger } from 'ngx-logger';
import { Subscription } from 'rxjs';
import { AuthorModel } from '../../models/author.model';
import { BookModel } from '../../models/book.model';
import { GenreModel } from '../../models/genre.model';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
export class BookEditComponent implements OnInit, OnDestroy {
  @Input() public display = false;
  @Input() public book: BookModel | null = null;
  @Input() public authors: AuthorModel[] = [];
  @Input() public genres: GenreModel[] = [];
  @Output() public displayChange = new EventEmitter<boolean>();
  @Output() public bookChange = new EventEmitter<BookModel>();

  public selectedAuthor: AuthorModel | undefined = undefined;
  public selectedGenres: GenreModel[] | undefined = undefined;
  public selectedRars: {name: string, value: number} | undefined = undefined;
  public selectedAccessLevel: {name: string, value: number} | undefined = undefined;

  public dialogHeader = '';
  public submitted = false;
  private subscriptions: Subscription = new Subscription();

  public rarsList = [
    {name: '0+', value: 0},
    {name: '6+', value: 6},
    {name: '12+', value: 12},
    {name: '16+', value: 16},
    {name: '18+', value: 18},
  ];

  public accessLevelList = [
    {name: 'свободный доступ', value: 2},
    {name: 'только специалисты', value: 1},
  ];

  public bookEditForm: FormGroup = new FormGroup({
    id: new FormControl(''),
    bookName: new FormControl('', Validators.required),
    rars: new FormControl(''),
    accessLevel: new FormControl('')
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
      if (this.book) {
        this.logger.info(`onDisplayChange: ${JSON.stringify(this.book)}`);

        this.dialogHeader = 'Редактировать книгу';
        this.bookEditForm.patchValue({
          id: this.book && this.book.id ? this.book.id : null,
          bookName: this.book.name,
        });
        this.selectedRars = this.rarsList.filter(item => item.value === this.book?.rars)[0];
        this.selectedAccessLevel = this.accessLevelList.filter(item => item.value === this.book?.accessLevel)[0];
        this.selectedAuthor = this.book.author;
        this.selectedGenres = this.book.genres;
      } else {
        this.dialogHeader = 'Добавить книгу';
        this.bookEditForm.patchValue({
          id: null,
          bookName: ''
        });
      }
    }
    this.submitted = false;
    this.display = val;
    this.displayChange.emit(val);
  }

  onBookChange() {
    this.submitted = true;
    const nameField = this.bookEditForm.get('bookName');
    const updateBook: BookModel = {
      id: this.book ? this.book.id : '0',
      name: nameField?.value,
      rars: this.selectedRars?.value ? this.selectedRars?.value : 18,
      accessLevel: this.selectedAccessLevel?.value ? this.selectedAccessLevel?.value : 2,
      author: this.selectedAuthor,
      genres: this.selectedGenres
    };
    if (this.bookEditForm.valid) {
      this.book = updateBook;
      this.bookChange.emit(updateBook);
      this.display = false;
    }
  }

  onAuthorChange(event: any) {

  }

  onGenreChange(event: any) {

  }

}
