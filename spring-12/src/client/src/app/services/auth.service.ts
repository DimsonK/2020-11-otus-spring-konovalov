import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { NGXLogger } from 'ngx-logger';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../models/user.model';

export interface AuthRequest {
  userName: string,
  password: string
}

export interface AuthResponse {
  result: string;
}

@Injectable({providedIn: 'root'})
export class AuthService {
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(
    private logger: NGXLogger,
    private router: Router,
    private http: HttpClient,
  ) {
    this.userSubject = new BehaviorSubject<User>(JSON.parse(<string>localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  public isLoggedIn(): boolean {
    const user = this.userSubject.getValue();
    return !!user;
  }

  public login(request: AuthRequest): Observable<any> {
    return this.http
      .post<User>(`/auth`, request)
      .pipe<any>(map((user: User) => {
          user.authData = window.btoa(request.userName + ':' + request.password);
          localStorage.setItem('user', JSON.stringify(user));
          this.userSubject.next(user);
          return user;
        })
      );

  }

  public logout(): void {
    localStorage.removeItem('user');
    this.userSubject.next(new User());
    this.router.navigate(['/login']).then();
  }

}
