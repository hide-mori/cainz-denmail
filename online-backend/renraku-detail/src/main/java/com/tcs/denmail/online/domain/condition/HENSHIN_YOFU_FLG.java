package com.tcs.denmail.online.domain.condition;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HENSHIN_YOFU_FLG {

    TRUE("1", "要", null),

    FALSE("0", "不要", "－");

    private String code;

    private String value;

    private String placeholder;

    public static String getValueByCode(String code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).map(e -> e.getValue()).findAny()
                .orElse(null);
    }
}
