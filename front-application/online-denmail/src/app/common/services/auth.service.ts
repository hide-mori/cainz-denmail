import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, of, Subject } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { HttpConstants } from '../constants/http-constants';
import { SessionStorageEnum } from '../constants/session-strage.enum';
import { Login } from '../model/login';
import { BaseService } from './base.service';

/*
import * as messageInfo from 'src/app/common/settings/message-info';
import { MessageUtility } from '../utility/message-utility';
*/

export class AuthResulted {
  isError: boolean = true;
  errorMessage: string = '';
  errorCode: string = '';
  loginInfoData: Login = null
}

@Injectable({
  providedIn: 'root',
})
export class AuthService extends BaseService {
  static readonly USER_TYPE_INSURANCE = '01';

  constructor(private http: HttpClient) {
    //,httpErrorHandler: HttpErrorHandler) {

    super();
    //this.handleError = httpErrorHandler.createHandleError('AuthService');
  }
  
  authResulted : AuthResulted = null;
  public getAuthResulted() :AuthResulted {
    return this.authResulted;
  }
  public loginAndAuthenticatePromise(login: Login): Promise<AuthResulted> {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
      })
    };
    const url = 'http://localhost:8080/checkloginuserpost';

    // login APIにPOSTする
    return this.http.post<Login>(url, login, httpOptions)
      .toPromise()
      .then((result: Login) => {
        // 認証結果がsuccessならトークンを返す
        if (result != null && result.shainCd != null && result.tenpoCd != null) {

          // 取得したトークンをLocalStorageに保存する
          this.saveLoginInfo(result)
          let rt = new AuthResulted();
          rt.loginInfoData = result;

          this.authResulted = rt;
          return rt;
        }
        // 認証結果がsuccessでなければエラーメッセージを返す
        else {
          let rt = new AuthResulted();
          rt.errorCode = "001";
          rt.errorMessage = "本部の方など、新伝メールへの接続不可です。";

          this.authResulted = rt;
          
          return Promise.reject(rt);
        }
      }
      )
      .catch((err: any) => {
        let rt = new AuthResulted();
        rt.errorCode = "999";
        rt.errorMessage = "システムエラー：" + (err != null ? err.toString() : " err is null");
        
        this.authResulted = rt;

        return Promise.reject(rt);
      }
      );
  }

  public loginAndAuthenticateObservable(login: Login): Observable<boolean> {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
      })
    };
    const url = 'http://localhost:8080/checkloginuserpost';

    // login APIにPOSTする
    return this.http.post<Login>(url, login, httpOptions)
      .pipe(
        map(result =>{
          // 認証結果がsuccessならトークンを返す
          if (result != null && result.shainCd != null && result.tenpoCd != null) {
  
            // 取得したトークンをLocalStorageに保存する
            this.saveLoginInfo(result)
            let rt = new AuthResulted();
            rt.loginInfoData = result;
  
            this.authResulted = rt;
            return true;
          }
          // 認証結果がsuccessでなければエラーメッセージを返す
          else {
            let rt = new AuthResulted();
            rt.errorCode = "001";
            rt.errorMessage = "本部の方など、新伝メールへの接続不可です。";
  
            this.authResulted = rt;
            
            return false;
          }
        }),
        catchError((err)=>{
          let rt = new AuthResulted();
          rt.errorCode = "999";
          rt.errorMessage = "システムエラー：" + (err != null ? err.toString() : " err is null");
          
          this.authResulted = rt;
          
          return of(false);
        })
      );
  }

  /**
   * 
   */
  public isTimeOut(): boolean {
    let last = sessionStorage.getItem(SessionStorageEnum.LAST_LOGIN_TIME);
    if (!last) {
      last = "2020/01/01 23:59:59";
    }
    let pass = Date.now() - Date.parse(last);
    // 30分 30*60*1000
    if (pass > 30 * 60 * 1000) {
      return true;
    }
    return false;
  }

  // public isAuthenticated(login: Login): Observable<Login> {
  public isAuthenticated22(): boolean {
    // const url = '/mb/common/image/get';

    // return this.http.post<Login>(url, login, HttpConstants.HTTP_OPTIONS);

    /*
    console.log(
      MessageUtility.getMessageWithParam(messageInfo.CH00001I, [
        'サンプルアプリ',
      ])
    );

    console.log(messageInfo.CM00005E.content);
    */
    return true;
  }

  public loginSubject = new Subject<Login>();

  public loginInfo: Login;


  login(shainCd: string): Observable<Login> {

    const url = 'http://localhost:8080/checkloginuser/';

    //return this.http.post<Login>(url, loginUser, HttpConstants.HTTP_OPTIONS);
    return (

      this.http.get<Login>(url, {
        params: {
          shainCd: shainCd,
        },
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      })

    );
  }

  logout(): void {
    this.clearSessionStorage();
  }

  /**
   * セッションストレージからログイン情報を取得する。
   */
  getSession(): Login {

    console.log("==================getSession()==================");
    if (this.isTimeOut()) {
      return null;
    }

    let tmpLogin = new Login();
    tmpLogin.shainCd = sessionStorage.getItem(SessionStorageEnum.LOGIN_INFO_USER_CODE);
    tmpLogin.userName = sessionStorage.getItem(SessionStorageEnum.LOGIN_INFO_USER_NAME);
    tmpLogin.tenpoCd = sessionStorage.getItem(SessionStorageEnum.LOGIN_INFO_TENPO_CODE);
    tmpLogin.tenpoName = sessionStorage.getItem(SessionStorageEnum.LOGIN_INFO_TENPO_NAME);
    tmpLogin.userType = sessionStorage.getItem(SessionStorageEnum.LOGIN_INFO_USER_TYPE);
    tmpLogin.lastLoginTime = sessionStorage.getItem(SessionStorageEnum.LAST_LOGIN_TIME);

    JSON.stringify(this.loginInfo);

    console.log(tmpLogin);
    console.log("==================getSession()==================end");
    return tmpLogin;
  }


  public clearSessionStorage() {
    sessionStorage.clear();
  }

  public saveLoginInfo(loginInfoParameter: Login) {
    // ブラウザ更新ボタン対策
    sessionStorage.setItem(SessionStorageEnum.LOGIN_INFO_USER_CODE, loginInfoParameter.shainCd);
    sessionStorage.setItem(SessionStorageEnum.LOGIN_INFO_USER_NAME, loginInfoParameter.userName);
    sessionStorage.setItem(SessionStorageEnum.LOGIN_INFO_USER_TYPE, loginInfoParameter.userType);
    sessionStorage.setItem(SessionStorageEnum.LOGIN_INFO_TENPO_CODE, loginInfoParameter.tenpoCd);
    sessionStorage.setItem(SessionStorageEnum.LOGIN_INFO_TENPO_NAME, loginInfoParameter.tenpoName);

    let tmpDate = new Date();
    let tmpJaDateString = tmpDate.toLocaleString("ja"); // 2019/2/28 23:59:59
    sessionStorage.setItem(SessionStorageEnum.LAST_LOGIN_TIME, tmpJaDateString);

    this.loginInfo = loginInfoParameter;
  }
}
