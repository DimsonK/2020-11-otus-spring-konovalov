import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = this.authService.userValue;
    const isLoggedIn = user && user.authData;
    const isApiUrl = request.url.startsWith('/api');
    const authData = user ? user.authData : null;
    if (isLoggedIn && isApiUrl && authData) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${authData}`
        }
      });
    }

    return next.handle(request);
  }
}
