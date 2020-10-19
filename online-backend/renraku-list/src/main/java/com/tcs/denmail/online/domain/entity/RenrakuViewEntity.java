package com.tcs.denmail.online.domain.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import lombok.Data;

@Data
@Entity
@Table(name = "vv_renraku")
public class RenrakuViewEntity {

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
    /** 発信社員名 */
    private String hasshinShainNm;
    /** 店長報告要フラグ */
    private String hokokuFlg;
    /** 連絡文書区分 */
    private String bunshoKbn;
    /** 宛先区分 */
    private String atesakiKbn;
    /** 品番 */
    private String hinban;
    /** 主題コード */
    private String shudaiCd;
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
    /** 返信期限 */
    private String henshinKigenYmd;
    /** 返信方法コード */
    private String henshinHohoCd;
    /** 棚割報告要不要フラグ */
    private String tanawariYofuFlg;
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

    /** 主題 */
    // @ManyToOne(fetch = FetchType.EAGER)
    // @Fetch(FetchMode.JOIN)
    // @JoinColumn(name = "shudaiCd", referencedColumnName = "shudaiCd", insertable = false,
    //         updatable = false)
    @Transient
    private ShudaiEntity shudai = new ShudaiEntity();

    /** 返信方法 */
    // @ManyToOne
    // @JoinColumnsOrFormulas({
    //         @JoinColumnOrFormula(column = @JoinColumn(name = "henshinHohoCd",
    //                 referencedColumnName = "nmCd", insertable = false, updatable = false)),
    //         @JoinColumnOrFormula(
    //                 formula = @JoinFormula(referencedColumnName = "nmKbn", value = "'003'"))})
    @Transient
    private NameMasterEntity henshinHoho = new NameMasterEntity();
}
