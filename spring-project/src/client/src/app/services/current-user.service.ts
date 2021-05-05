import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserModel } from '../models/user.model';
import { AuthService } from './auth.service';

@Injectable()
export class CurrentUserService {
  constructor(
    private logger: NGXLogger,
    private http: HttpClient,
    private auth: AuthService,
  ) {
  }

  public loadCurrentUser(): Observable<UserModel> {
    return this.http
      .get<UserModel>('/api/user', {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: UserModel) => data));
  }

  public updateCurrentUser(payload: UserModel): Observable<UserModel> {
    return this.http
      .put<UserModel>(`/api/user/${payload.id}`, payload, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: UserModel) => data));
  }

  public deleteCurrentUser(payload: string) {
    return this.http
      .delete<string>(`/api/user/${payload}`, {
        headers: {Authorization: `Bearer ${this.auth.getToken()}`},
      })
      .pipe(map((data: string) => data));
  }
}
