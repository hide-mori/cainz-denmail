import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { AuthService } from '../services/auth.service';
/**
 * HTTPリクエストInterceptor.
 * @export
 * @classdesc HttpRequestInterceptor HTTPリクエストInterceptor.
 * @implements {HttpInterceptor}
 */
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  /**
   * コンストラクタ.
   * @param {AuthService} authService
   * @memberof HttpRequestInterceptor
   */
  constructor(private authService: AuthService) {}

  /**
   * HTTPリクエストヘッダにCognito認証トークンを付加する.
   * @param {HttpRequest<any>} req HTTPリクエスト
   * @param {HttpHandler} next 次のハンドラ
   * @returns {Observable<HttpEvent<any>>} 次のハンドラ
   * @memberof HttpRequestInterceptor
   */
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // // Cognito認証トークン取得
    // const token = this.authService.getCognitoToken();
    // // Cognito認証トークンを加えたリクエストを複製する
    // let authReq = req.clone({ setHeaders: {
    //         'x-mb-authorization': token.idToken,
    //         'x-mb-sess-authorization': token.refreshToken
    //     }
    // });
    // 複製したHTTPリクエストを送信する
    return next.handle(req);
  }
}
