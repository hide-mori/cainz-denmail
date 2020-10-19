package com.tcs.denmail.online.app.model;

import lombok.Data;

@Data
public class RenrakuDetailModel {

    /** タイトル */
    private String title;
    /** 管理番号 */
    private String kanriNo;
    /** 店舗コード＋店舗名 */
    private String tenpoAll;
    /** 店着日 */
    private String tenchakuYmd;
    /** 発信部署コード */
    private String hasshinBushoCd;
    /** 発信部署名略 */
    private String hasshinBushoNm;
    /** 発信部署名正式 */
    private String hasshinBushoNmAll;
    /** 発信社員名 */
    private String hasshinShainNm;
    /** 連絡文書区分 */
    private String bunshoKbn;
    /** 宛先区分 */
    private String atesakiKbn;
    /** 品番 */
    private String hinban;
    /** 主題コード */
    private String shudaiCd;
    /** 主題名 */
    private String shudaiNm;
    /** 件名 */
    private String kenmei;
    /** 連絡文 */
    private String renrakubun;
    /** 店舗作業 */
    private String tenpoSagyo;
    /** 手順 */
    private String tejun;
    /** その他連絡文書 */
    private String sonotaRenraku;
    /** 返信要不要フラグ */
    private String henshinYofuFlg;
    /** 返信要不要TEXT */
    private String henshinYofuText;
    /** 返信期限 */
    private String henshinKigenYmd;
    /** 返信方法コード */
    private String henshinHohoCd;
    /** 返信方法TEXT */
    private String henshinHohoText;
    /** 棚割報告要不要フラグ */
    private String tanawariYofuFlg;
    /** 棚割報告要不要TEXT */
    private String tanawariYofuText;
    /** 作業開始日 */
    private String sagyoFromYmd;
    /** 作業終了日 */
    private String sagyoToYmd;
    /** 添付ファイル有無フラグ */
    private String tenpuFileFlg;
    /** 連絡区分 */
    private String renrakuKbn;
    /** 緊急発信フラグ */
    private String kinkyuHasshinFlg;
    /** 作成社員コード */
    private String sakuseiShainCd;
    /** 承認フラグ */
    private String shoninFlg;
    /** 承認社員コード */
    private String shoninShainCd;
    /** 表示用宛先 */
    private String dispAtesaki;
    /** 表示用対象 */
    private String dispTaisho;
}
