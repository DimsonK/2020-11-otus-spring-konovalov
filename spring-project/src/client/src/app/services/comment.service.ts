import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CommentModel } from '../models/comment.model';
import { AuthService } from './auth.service';

@Injectable()
export class CommentService {
  constructor(
    private logger: NGXLogger,
    private http: HttpClient,
    private auth: AuthService,
  ) {
  }

  public loadComments(payload: string): Observable<CommentModel[]> {
    return this.http
      .get<CommentModel[]>(`/api/comment/book/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: CommentModel[]) => data));
  }

  public loadCommentById(payload: string): Observable<CommentModel> {
    return this.http
      .get<CommentModel>(`/api/comment/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: CommentModel) => data));
  }

  public createComment(payload: CommentModel): Observable<CommentModel> {
    return this.http
      .post<CommentModel>('/api/comment', payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: CommentModel) => data));
  }

  public updateComment(payload: CommentModel): Observable<CommentModel> {
    return this.http
      .put<CommentModel>(`/api/comment/${payload.id}`, payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: CommentModel) => data));
  }

  public deleteComment(payload: string) {
    return this.http
      .delete<string>(`/api/comment/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: string) => data));
  }
}
