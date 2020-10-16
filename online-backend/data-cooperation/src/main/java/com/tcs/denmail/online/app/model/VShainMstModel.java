package com.tcs.denmail.online.app.model;

import lombok.Data;

@Data
public class VShainMstModel {

    private String tenCd;
    private String tenRyakuMeiKj;
    private String shainCd;
    private String shainNm;
    private String jigyoHonbuCd;
    private String areaCd;
    private String honBushoCd;
    private Integer shokuiDankaiLevel;
    private String shokuiCd;
    private String shokuiNm;
    private String dmlShokuiCd;
    private String dmlShokuiNm;
    private String bushoCd;
    private String bushoNm;
    private String spFlg;
    private String realTimeLinkDeleteFlg;
}
