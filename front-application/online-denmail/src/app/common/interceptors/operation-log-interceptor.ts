import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';
import { tap, finalize } from 'rxjs/operators';
import { Observable } from 'rxjs';
import * as messageInfo from 'src/app/common/settings/message-info.json';
import { MessageUtility } from '../utility/message-utility';
/**
 * @export
 * @class OperationLogInterceptor 操作ログ出力インターセプター
 * @implements {HttpInterceptor}
 */
@Injectable()
export class OperationLogInterceptor implements HttpInterceptor {
  /**
   * 操作ログ出力メソッド
   * @param {HttpRequest<any>} req HTTPリクエスト
   * @param {HttpHandler} next HTTP後続処理ハンドラー
   * @returns {Observable<HttpEvent<any>>} HTTPレスポンス
   * @memberof OperationLogInterceptor
   */
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const started = Date.now();
    // 開始ログ
    console.log(
      MessageUtility.getMessageWithParam(messageInfo.FW00001D, [req.url])
    );

    // リクエストボディのダンプ
    console.log(req.body);
    return next.handle(req).pipe(
      tap((event) => {
        if (event instanceof HttpResponse) {
          // レスポンスボディのダンプ
          console.log(event.body);
          const elapsed = Date.now() - started;
          // 終了ログ
          console.log(
            MessageUtility.getMessageWithParam(messageInfo.FW00002D, [
              req.url,
              elapsed.toString(),
            ])
          );
        }
      })
    );
  }
}
