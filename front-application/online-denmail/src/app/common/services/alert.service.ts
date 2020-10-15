import { Injectable } from '@angular/core';

import { AlertType } from '../constants/alert.type.enum';
import { Alert } from '../model/alert';
import { BaseService } from './base.service';

/**
 * @classdesc AlertService アラートサービス
 */
@Injectable({
  providedIn: 'root',
})
export class AlertService extends BaseService {
  /**
   * アラート情報保管配列
   * @type {Alert[]}
   * @memberof AlertService
   */
  public alerts: Alert[];

  /**
   * コンストラクタ
   * @memberof AlertService
   */
  constructor() {
    super();
    this.alerts = [];
  }

  /**
   * 受け取ったアラート情報をアラート情報保管配列に追加する。
   * @param {Alert} alert アラート情報
   * @memberof AlertService
   */
  public push(alert: Alert) {
    this.alerts.shift();
    this.alerts.push(alert);
    if (alert.type === AlertType.SUCCESS || alert.type === AlertType.INFO) {
      setTimeout(() => {
        this.popAll();
      }, 5000);
    }
  }

  /**
   * 全てのアラート表示をクリアする。
   * @memberof AlertService
   */
  public popAll() {
    this.alerts = [];
  }

  /**
   * 設定ファイルのメッセージ情報から、
   * アラート表示時のBootStrapのスタイル名を返す。
   * @param {*} msg メッセージ情報
   * @returns {AlertType} アラートタイプ
   * @memberof AlertService
   */
  public messageTypeToStyle(msg): AlertType {
    let result: AlertType = null;

    switch (msg.type) {
      case 'INFO':
        result = AlertType.INFO;
        break;
      case 'ERROR':
        result = AlertType.DANGER;
        break;
      default:
        result = AlertType.PRIMARY;
        break;
    }
    return result;
  }

  /**
   * アラート情報保管配列が空の場合true、それ以外の場合falseを戻す
   * @memberof AlertService
   */
  public isAlertEmpty(): boolean {
    return this.alerts.length === 0;
  }
}
