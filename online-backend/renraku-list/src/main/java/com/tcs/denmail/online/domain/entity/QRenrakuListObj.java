package com.tcs.denmail.online.domain.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QRenrakuListObj {
    @Id
    @Column(name="kanri_no")
    // 管理番号
    private String kanriNo;
    // 作業開始日
    private String sagyoFromYmd;
    // 作業終了日
    private String sagyoToYmd;
    // 主題コード
    private String shudaiCd;
    // 主題名
    private String shudaiNm;
    // 品番
    private String hinban;
    // 件名
    private String kenmei;
    
    // 1~6
    private String statusId;
    private String dbStatusId;
    private String shinchokuKbn;
    private String taishogaiFlg;
    private String sagyoShinchokuKbn;
    private String sagyoTaishogaiFlg;
}
