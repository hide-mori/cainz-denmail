package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 連絡文書参照状況
 */
@Data
@Entity
@Table(name = "t_renraku_jokyo")
@IdClass(RenrakuJokyoEntity.Pk.class)
public class RenrakuJokyoEntity {

    @Id
    private String kanriNo;
    @Id
    private String atesakiTenpoCd;
    
    private String atesakiTenpoRyakuNm;
    private String henshinShainCd;
    private String henshinShainNm;
    private String henshinbun;
    private String shinchokuKbn;
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
    public static class Pk implements Serializable{

        private static final long serialVersionUID = 1L;

        private String kanriNo;
        
        private String atesakiTenpoCd;
    }
}
