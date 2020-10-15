package com.tcs.denmail.online.domain.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BUNSHO_KBN {

    /** 連絡文書 */
    RENRAKU("0"),
    /** 作業指示 */
    SAGYO("1");

    private String code;
}
