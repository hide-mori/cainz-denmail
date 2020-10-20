package com.tcs.denmail.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

import com.tcs.denmail.common.exception.TcsSystemException;

public class PropertyUtil {

    private static final String INIT_FILE_NAME = "common.properties";
    private static Properties properties;

    private PropertyUtil() throws Exception {
    }

    static {
        properties = new Properties();
        List<File> fileList = FileUtil.findPropertiesFile(".", INIT_FILE_NAME);
        if (fileList.isEmpty()) {
            // ファイル検索に失敗
            throw new TcsSystemException("ファイルの検索に失敗しました。ファイル名:" + INIT_FILE_NAME);
        }
        try {
            properties.load(Files.newBufferedReader(fileList.get(0).toPath(), StandardCharsets.UTF_8));
        } catch (final IOException e) {
            properties.clear();
            // ファイル読み込みに失敗
            throw new TcsSystemException("ファイルの読み込みに失敗しました。ファイル名:" + INIT_FILE_NAME, e.getCause());
        }
    }

    /**
     * プロパティ値を取得する
     *
     * @param key キー
     * @return キーが存在しない場合、デフォルト値 存在する場合、値
     */
    public static String getProperty(final String key) {
        return properties.getProperty(key);
    }
}
