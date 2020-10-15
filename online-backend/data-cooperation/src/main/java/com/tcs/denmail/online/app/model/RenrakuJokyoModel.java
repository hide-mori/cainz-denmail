package com.tcs.denmail.online.app.model;

import lombok.Data;

@Data
public class RenrakuJokyoModel {
    private String kanriNo;
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
    private String realTimeLinkDeleteFlg;
}
