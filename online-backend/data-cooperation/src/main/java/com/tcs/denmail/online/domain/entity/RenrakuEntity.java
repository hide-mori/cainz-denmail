package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_renraku")
public class RenrakuEntity extends TcsBaseEntity {

    @Id
    @NotNull
    private String kanriNo;
    @NotNull
    private String tenchakuYmd;
    private String hasshinBushoCd;
    private String hasshinBushoNm;
    private String hasshinBushoNmAll;
    private String hasshinShainNm;
    @NotNull
    private String dengonbanFlg;
    @NotNull
    private String hokokuFlg;
    @NotNull
    private String bunshoKbn;
    @NotNull
    private String atesakiKbn;
    private String atesaki;
    private String hinban;
    @NotNull
    private String shudaiCd;
    @NotNull
    private String kenmei;
    private String renrakubun;
    private String tenpoSagyo;
    private String tejun;
    private String sonotaRenraku;
    @NotNull
    private String henshinYofuFlg;
    private String henshinKigenYmd;
    private String henshinHohoCd;
    @NotNull
    private String tanawariYofuFlg;
    private String sagyoFromYmd;
    private String sagyoToYmd;
    @NotNull
    private String tenpuFileFlg;
    @NotNull
    private String renrakuKbn;
    @NotNull
    private String kinkyuHasshinFlg;
    @NotNull
    private String sakuseiShainCd;
    @NotNull
    private String shoninFlg;
    private String shoninShainCd;
    @NotNull
    private String delFlg;

}
