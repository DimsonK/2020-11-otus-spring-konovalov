import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NGXLogger } from 'ngx-logger';
import { Subscription } from 'rxjs';
import { BookModel } from '../../models/book.model';
import { CommentModel } from '../../models/comment.model';

@Component({
  selector: 'app-comment-edit',
  templateUrl: './comment-edit.component.html',
  styleUrls: ['./comment-edit.component.css']
})
export class CommentEditComponent implements OnInit, OnDestroy {
  @Input() public display = false;
  @Input() public comment: CommentModel | null = null;
  @Input() public selectedBook: BookModel | null = null;
  @Output() public displayChange = new EventEmitter<boolean>();
  @Output() public commentChange = new EventEmitter<CommentModel>();

  public dialogHeader = '';
  public submitted = false;
  private subscriptions: Subscription = new Subscription();

  public commentEditForm: FormGroup = new FormGroup({
    id: new FormControl(''),
    commentDate: new FormControl('', Validators.required),
    commentAuthor: new FormControl('', Validators.required),
    commentContent: new FormControl('', Validators.required),
    commentFavorite: new FormControl(false),
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
      if (this.comment) {
        this.logger.info(`onDisplayChange: ${JSON.stringify(this.comment)}`);

        this.dialogHeader = 'Редактировать комментарий';
        this.commentEditForm.patchValue({
          id: this.comment && this.comment.id ? this.comment.id : null,
          commentDate: this.comment.postDate,
          commentAuthor: this.comment.authorName,
          commentContent: this.comment.content,
          commentFavorite: this.comment.favorite,
        });
      } else {
        this.dialogHeader = 'Добавить комментарий';
        this.commentEditForm.patchValue({
          id: null,
          commentDate: '',
          commentAuthor: '',
          commentContent: '',
          commentFavorite: false,
        });
      }
    }
    this.submitted = false;
    this.display = val;
    this.displayChange.emit(val);
  }

  onCommentChange() {
    this.submitted = true;
    const updateComment: CommentModel = {
      id: this.comment ? this.comment.id : '0',
      postDate: this.commentEditForm.get('commentDate')?.value,
      authorName: this.commentEditForm.get('commentAuthor')?.value,
      content: this.commentEditForm.get('commentContent')?.value,
      favorite: this.commentEditForm.get('commentFavorite')?.value,
      bookId: this.selectedBook ? this.selectedBook?.id : '0',
    };
    if (this.commentEditForm.valid) {
      this.comment = updateComment;
      this.commentChange.emit(updateComment);
      this.display = false;
    }
  }

}
