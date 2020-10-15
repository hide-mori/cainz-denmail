import { Injectable } from '@angular/core';

import { ShainInfoData, RenrakuBunshouData } from "../modal/renrakuData";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  loginInfo: ShainInfoData;

  constructor() { 
    this.loginInfo = new ShainInfoData();
    //shainCd: string = "01037233";
    let shainCd: string = "10147718";
    
    this.loginInfo.shainCd = shainCd;
    this.loginInfo.shainNam = "三浦　カツオ";
    this.loginInfo.tenCd = "0001";
    this.loginInfo.tenRyakuMeiKj = "浦和美園店"; //"カインズホーム浦和美園店";
  }

  getLoginInfo(){
    return this.loginInfo;
  }
  
}
