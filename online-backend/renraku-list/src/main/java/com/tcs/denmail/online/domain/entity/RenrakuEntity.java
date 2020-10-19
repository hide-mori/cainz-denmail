package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "VV_RENRAKU")
public class RenrakuEntity {

    /** 管理番号 */
    @Id
    private String kanriNo;
    /** 店着日 */
    private String tenchakuYmd;
    /** 発信部署コード */
    private String hasshinBushoCd;
    /** 発信部署名略 */
    private String hasshinBushoNm;
    /** 発信部署名正式 */
    private String hasshinBushoNmAll;
    /** 発信者名 */
    private String hasshinShainNm;
    /** 品番 */
    private String hinban;
    /** 主題コード */
    private String shudaiCd;
    /** 件名 */
    private String kenmei;
    /** 作業開始日 */
    private String sagyoFromYmd;
    /** 作業終了日 */
    private String sagyoToYmd;
    /** 店舗作業内容 */
    private String tenpoSagyo;
    /** 手順 */
    private String tejun;
    /** その他連絡文書 */
    private String sonotaRenraku;
    /** 添付ファイル有無フラグ */
    private String tenpuFileFlg;
    /** 返信要不要フラグ */
    private String henshinYofuFlg;
    /** 返信期限 */
    private String henshinKigenYmd;
    /** 返信方法コード */
    private String henshinHohoCd;
    /** 棚割報告要不要フラグ */
    private String tanawariYofuFlg;
    /** 表示用宛先 */
    @Transient
    private String dispAtesaki;
    /** 表示用対象 */
    @Transient
    private String dispTaisho;

}
