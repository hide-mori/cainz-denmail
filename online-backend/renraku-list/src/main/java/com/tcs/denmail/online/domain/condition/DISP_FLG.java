package com.tcs.denmail.online.domain.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DISP_FLG {

    TRUE("1"),
    
    FALSE("0");

    private String code;

}
