import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { NGXLogger } from 'ngx-logger';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({providedIn: 'root'})
export class AdminGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService,
    private logger: NGXLogger
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const user = this.authService.userValue;
    if (user && user.authData && user.roles?.indexOf('ADMIN') !== -1) {
      this.logger.debug(`logged user: ${JSON.stringify(user)}`);
      return true;
    }
    this.router.navigate(['/home'], {queryParams: {returnUrl: state.url}}).then();
    return false;
  }
}
