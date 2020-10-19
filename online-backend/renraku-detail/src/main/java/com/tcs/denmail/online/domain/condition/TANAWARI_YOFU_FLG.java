package com.tcs.denmail.online.domain.condition;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TANAWARI_YOFU_FLG {

    TRUE("1", "要"),

    FALSE("0", "不要");

    private String code;

    private String value;

    public static String getValueByCode(String code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).map(e -> e.getValue()).findAny()
                .orElse(null);
    }
}
