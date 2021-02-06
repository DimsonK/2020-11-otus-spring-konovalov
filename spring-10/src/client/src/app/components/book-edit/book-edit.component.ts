import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  @Input() public book: BookModel = new BookModel();
  @Input() public authors: AuthorModel[] = [];
  @Input() public author: AuthorModel | null = null;
  @Input() public genres: GenreModel[] = [];
  @Input() public genre: GenreModel | null = null;
  @Output() public displayChange = new EventEmitter<boolean>();
  @Output() public bookChange = new EventEmitter<BookModel>();

  public selectedAuthor: AuthorModel | null = null;
  public selectedGenre: GenreModel | null = null;

  public bookForm: FormGroup = this.formBuilder.group({
    id: null,
    name: ['', Validators.required],
  });
  public dialogHeader = '';
  public submitted = false;
  private subscriptions: Subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
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
        this.dialogHeader = 'Редактировать книгу';
        this.bookForm.patchValue({
          id: this.book && this.book.id ? this.book.id : null,
          name: this.book.name,
        });
      } else {
        this.dialogHeader = 'Добавить книгу';
        this.bookForm.patchValue({
          id: null,
          name: '',
        });
      }
    }
    this.submitted = false;
    this.display = val;
    this.displayChange.emit(val);
  }

  onBookChange() {
    this.submitted = true;
    const updateBook: BookModel = {
      id: this.book.id,
      name: '', //this.bookForm.get('name').value,
      author: new AuthorModel,
      genres: [new GenreModel()],
    };
    if (this.bookForm.valid) {
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
