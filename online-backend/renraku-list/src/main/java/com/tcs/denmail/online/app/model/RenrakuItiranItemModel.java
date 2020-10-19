package com.tcs.denmail.online.app.model;

import lombok.Data;

@Data
public class RenrakuItiranItemModel {
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

    private String dspKenmei;
}
