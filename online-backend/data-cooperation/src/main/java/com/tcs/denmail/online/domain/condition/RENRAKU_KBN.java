package com.tcs.denmail.online.domain.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RENRAKU_KBN {

    /** 連絡文書または作業指示書 */
    RENRAKU_BUNSHO("1"),

    /** 店長メール */
    TENCHO_MAIL("2");

    private String code;
}
