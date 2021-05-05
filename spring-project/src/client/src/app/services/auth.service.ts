import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { NGXLogger } from 'ngx-logger';
import { BehaviorSubject, Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

export interface AuthRequest {
  username: string,
  password: string
}

export interface UserDetails {
  id: string;
  username: string;
  email: string;
  roles: string[];
  jti: string;
  exp: number;
  iat: number;
}

interface TokenResponse {
  token: string;
}

@Injectable({providedIn: 'root'})
export class AuthService {
  private userSubject: BehaviorSubject<UserDetails | null>;
  public user: Observable<UserDetails | null>;
  private token: string | null = null;

  constructor(
    private logger: NGXLogger,
    private router: Router,
    private http: HttpClient,
  ) {
    this.userSubject = new BehaviorSubject<UserDetails | null>(this.getUserDetails());
    this.user = this.userSubject.asObservable();
  }

  private saveToken(token: string): void {
    localStorage.setItem('mean-token', token);
    this.token = token;
  }

  public getToken(): string | null {
    if (!this.token) {
      this.token = localStorage.getItem('mean-token');
    }
    return this.token;
  }

  public getUserDetails(): UserDetails | null {
    const token = this.getToken();
    if (token) {
      const base64url = token.split('.')[1];
      const base64 = base64url.replace(/-/gi, '+').replace(/_/gi, '/');
      return JSON.parse(
        decodeURIComponent(
          atob(base64)
            .split('')
            .map((c) => `%${`00${c.charCodeAt(0).toString(16)}`.slice(-2)}`)
            .join('')
        )
      );
    }
    return null;
  }

  public get userValue(): UserDetails | null {
    return this.userSubject.value;
  }

  public isLoggedIn(): boolean {
    const user = this.getUserDetails();
    if (user && user.exp) {
      return user.exp > Date.now() / 1000;
    }
    return false;
  }

  public login(request: AuthRequest): Observable<any> {
    return this.http
      .post<TokenResponse>(`/auth`, request)
      .pipe<any>(map((data: TokenResponse) => {
          if (data.token) {
            this.saveToken(data.token);
            this.userSubject.next(this.getUserDetails());
          }
          return data;
        })
      );
  }

  public logout(): void {
    this.http
      .post('/auth/logout', {})
      .pipe(
        finalize(() => {
          localStorage.removeItem('mean-token');
          this.token = null;
          this.userSubject.next(null);
          this.router.navigate(['/home']).then();
        }))
      .subscribe();
  }

}
