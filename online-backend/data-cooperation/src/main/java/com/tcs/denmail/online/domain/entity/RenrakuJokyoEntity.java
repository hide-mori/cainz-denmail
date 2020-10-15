package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 連絡文書参照状況
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_renraku_jokyo")
@IdClass(RenrakuJokyoEntity.Pk.class)
public class RenrakuJokyoEntity extends TcsBaseEntity {

    @Id
    @NotNull
    private String kanriNo;
    @Id
    @NotNull
    private String atesakiTenpoCd;
    @NotNull
    private String atesakiTenpoRyakuNm;
    private String henshinShainCd;
    private String henshinShainNm;
    private String henshinbun;
    private String shinchokuKbn;
    @NotNull
    private String taishogaiFlg;
    private String firstOutputYmd;
    private String kanryoYmd;
    private String sagyoShainCd;
    private String sagyoShainNm;
    private String sagyoShinchokuKbn;
    private String sagyoTaishogaiFlg;
    private Integer sagyoNinji;
    private String kanryoShainCd;
    private String kanryoShainNm;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        private static final long serialVersionUID = 1L;

        private String kanriNo;

        private String atesakiTenpoCd;
    }
}
