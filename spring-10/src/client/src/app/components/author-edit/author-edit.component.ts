import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NGXLogger } from 'ngx-logger';
import { Subscription } from 'rxjs';
import { AuthorModel } from '../../models/author.model';

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrls: ['./author-edit.component.css']
})
export class AuthorEditComponent implements OnInit, OnDestroy {
  @Input() public display = false;
  @Input() public author: AuthorModel | null = null;
  @Output() public displayChange = new EventEmitter<boolean>();
  @Output() public authorChange = new EventEmitter<AuthorModel>();

  public dialogHeader = '';
  public submitted = false;
  private subscriptions: Subscription = new Subscription();

  public authorEditForm: FormGroup = new FormGroup({
    id: new FormControl(''),
    authorName: new FormControl('', Validators.required),
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
      if (this.author) {
        this.logger.info(`onDisplayChange: ${JSON.stringify(this.author)}`);

        this.dialogHeader = 'Редактировать автора';
        this.authorEditForm.patchValue({
          id: this.author && this.author.id ? this.author.id : null,
          authorName: this.author.name,
        });
      } else {
        this.dialogHeader = 'Добавить автора';
        this.authorEditForm.patchValue({
          id: null,
          authorName: ''
        });
      }
    }
    this.submitted = false;
    this.display = val;
    this.displayChange.emit(val);
  }

  onAuthorChange() {
    this.submitted = true;
    const nameField = this.authorEditForm.get('authorName');
    const updateAuthor: AuthorModel = {
      id: this.author ? this.author.id : '0',
      name: nameField?.value,
    };
    if (this.authorEditForm.valid) {
      this.author = updateAuthor;
      this.authorChange.emit(updateAuthor);
      this.display = false;
    }
  }
}
