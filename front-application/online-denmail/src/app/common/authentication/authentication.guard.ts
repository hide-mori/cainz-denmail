import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { debug } from 'console';
import { Observable, of } from 'rxjs';
import { AuthService, AuthResulted } from '../services/auth.service';
import { Login } from '../model/login';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationGuard implements CanActivate {
  constructor(public authService: AuthService, public router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    let result: boolean;

    let url = next.routeConfig.path;
    let logStartMsg = "AuthenticationGuard.canActivate-------";
    console.log(logStartMsg + "START  url=" + url);

    let check: AuthResulted = this.authService.getAuthResulted();

    //初期チェック
    if (check == null) {
      const id = next.queryParamMap.get('id');
      if (id == null) {
        let erroMsg: string = "ログインユーザIDが未設定！！";
        console.log(logStartMsg + erroMsg);
        this.router.navigate(['login/authentication-error', erroMsg]);
        return false;
      }

      let loginP = new Login();
      loginP.shainCd = id;

      return this.authService.loginAndAuthenticatePromise(loginP)
        .then((authRes: AuthResulted) => {

          console.log(logStartMsg + "end");
          return true;

        }).catch((authRes: AuthResulted) => {

          let erroMsg: string = authRes.errorMessage;;
          console.log(logStartMsg + erroMsg);
          console.log(logStartMsg + "end");
          this.router.navigate(['login/authentication-error', erroMsg]);
          return false;
        }
        );
    } else {

      if (this.authService.isTimeOut()) {
        this.router.navigate(['login/session-timeout']);
        return of(false);
      }

      let sessionLogin = this.authService.getSession();
      if (sessionLogin == null ||
        sessionLogin.shainCd == null ||
        sessionLogin.shainCd == '') {

        this.router.navigate(['login/session-timeout']);
        return of(false);

      } else {
        result = true;
        return of(true);
      }
    }
  }
}
