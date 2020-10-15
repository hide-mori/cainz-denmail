import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import * as messageInfo from '../settings/message-info.json';
import { MessageUtility } from '../utility/message-utility';
import { Router } from '@angular/router';
import { SessionStorageEnum } from '../constants/session-strage.enum';

/**
 * @export
 * @classdesc ErrorInterceptor 例外インターセプター
 * @implements {HttpInterceptor}
 */
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(public router: Router) {}

  /**
   * 例外ハンドリングメソッド
   * @param {HttpRequest<any>} request HTTPリクエスト
   * @param {HttpHandler} next HTTP後続処理ハンドラー
   * @returns {Observable<HttpEvent<any>>} HTTPレスポンス
   * @memberof ErrorInterceptor
   */
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const req = request.clone();
    return next.handle(req).pipe(
      catchError((res) => {
        if (res instanceof HttpErrorResponse) {
          if (res.error) {
            const role = sessionStorage.getItem(SessionStorageEnum.ROLE);
            const target = this.router.config.find(
              (item) => item.path.indexOf(role) > -1
            );
            switch (res.error.code) {
              case 400:
                // 業務エラー
                console.error(
                  MessageUtility.getMessageWithParam(messageInfo.FW00012E, [
                    res.error.message,
                  ])
                );
                break;
              case 401:
                // リンクアクセス時の認証エラー
                console.error(
                  MessageUtility.getMessageWithParam(messageInfo.FW00002F, [
                    res.error.message,
                  ])
                );
                this.router.navigate(['login/authentication-error']);
                break;
              case 403:
                // アプリ利用中の認証タイムアウトエラー
                console.error(
                  MessageUtility.getMessageWithParam(messageInfo.FW00012E, [
                    res.error.message,
                  ])
                );
                this.router.navigate(['login/session-timeout']);
                console.error('権限エラー');
                break;

              case 500:
                // システムエラー
                console.error(
                  MessageUtility.getMessageWithParam(messageInfo.FW00002F, [
                    res.error.message,
                  ])
                );
                // システムエラー画面に遷移
                this.router.navigate([target.path + '/system-error']);
                break;

              case 503:
                // メンテナンス中
                // console.error(MessageUtility.getMessageWithParam(messageInfo.FW00021E,
                //   [res.error.message]));
                // メンテナンス中画面に遷移
                this.router.navigate(['login/maintenance']);
                break;

              default:
                // 上記でハンドリングできないエラーレスポンスは全てをコンソールエラー表示
                console.error(
                  MessageUtility.getMessageWithParam(messageInfo.FW00002F, [
                    JSON.stringify(res.error),
                  ])
                );
                // システムエラー画面に遷移
                this.router.navigate([target.path + '/system-error']);
                break;
            }
          }
        }
        return throwError(res);
      })
    );
  }
}
