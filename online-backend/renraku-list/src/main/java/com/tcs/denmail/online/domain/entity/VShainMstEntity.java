
/*
社員マスタビュー	
V_SHAIN_MST	

店舗コード	TEN_CD
店舗名_略称	TEN_RYAKU_MEI_KJ
社員コード	SHAIN_CD
社員名	SHAIN_NM
事業部コード	JIGYO_HONBU_CD
エリアコード	AREA_CD
本部部署コード	HON_BUSHO_CD
職位段階レベル	SHOKUI_DANKAI_LEVEL
職位コード	SHOKUI_CD
職位名	SHOKUI_NM
でんメール職位コード	DML_SHOKUI_CD
でんメール職位名	DML_SHOKUI_NM
部署コード	BUSHO_CD
部署名	BUSHO_NM
*/
package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "V_SHAIN_MST")
public class VShainMstEntity {
    /** 社員コード */
    @Id
    private String shainCd;
    /** 社員名	SHAIN_NM */
    private String ShainNm;
    /** 店舗コード  */
    private String tenCd;
    /** 店舗名_略称  */
    private String tenRyakuMeiKj;
    /** 事業部コード */
    private String jigyoHonbuCd;
    /**でんメール職位コード */
    private String dmlShokuiCd;
    /**でんメール職位名*/
    private String dmlShokuiNm;
    /** 特殊フラグ */
    private String spFlg;

    /*
エリアコード	AREA_CD
本部部署コード	HON_BUSHO_CD
職位段階レベル	SHOKUI_DANKAI_LEVEL
職位コード	SHOKUI_CD
職位名	SHOKUI_NM
部署コード	BUSHO_CD
部署名	BUSHO_NM
    **/
}
