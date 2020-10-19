package com.tcs.denmail.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static String toString(Integer data) {
        return data == null ? null : data.toString();
    }

    public static String toString(LocalDate data) {
        return data == null ? null : data.format(DATE_TIME_FORMATTER_YYYY_MM_DD);
    }
}
