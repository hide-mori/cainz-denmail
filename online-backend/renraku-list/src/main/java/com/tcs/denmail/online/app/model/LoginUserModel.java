package com.tcs.denmail.online.app.model;


import lombok.Data;

@Data
public class LoginUserModel {

  /** ログインユーザーID */
  private String shainCd;

  /** ログインユーザー */
  private String userName;

  /** ユーザータイプ*/
  private String userType;

  /** 支店コード*/
  private String tenpoCd;

  /** 支店名*/
  private String tenpoName;
}
