import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CommentModel } from '../models/comment.model';

@Injectable()
export class CommentService {
  constructor(
    private http: HttpClient,
  ) {
  }

  public loadComments(payload: string): Observable<CommentModel[]> {
    return this.http
      .get<CommentModel[]>(`/api/comment/book/${payload}`)
      .pipe(map((data: CommentModel[]) => data));
  }

  public loadCommentById(payload: string): Observable<CommentModel> {
    return this.http
      .get<CommentModel>(`/api/comment/${payload}`)
      .pipe(map((data: CommentModel) => data));
  }

  public createComment(payload: CommentModel): Observable<CommentModel> {
    return this.http
      .post<CommentModel>('/api/comment', payload)
      .pipe(map((data: CommentModel) => data));
  }

  public updateComment(payload: CommentModel): Observable<CommentModel> {
    return this.http
      .put<CommentModel>(`/api/comment/${payload.id}`, payload)
      .pipe(map((data: CommentModel) => data));
  }

  public deleteComment(payload: string) {
    return this.http
      .delete<string>(`/api/comment/${payload}`)
      .pipe(map((data: string) => data));
  }
}
