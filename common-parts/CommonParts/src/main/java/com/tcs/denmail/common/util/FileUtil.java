package com.tcs.denmail.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FileUtil {

    private FileUtil() throws Exception {
    }

    /**
     * 与えられたディレクトリの下にあるファイルを再帰的に探索する。
     * 
     * @param absolutePath ディレクトリの絶対パス。
     * @return ファイルの一覧
     */
    public static List<File> findPropertiesFile(String absolutePath, String fileName) {

        List<File> files = new ArrayList<>();

        Stack<File> stack = new Stack<>();
        stack.add(new File(absolutePath));
        while (!stack.isEmpty()) {
            File item = stack.pop();
            if (item.isFile() && item.getName().indexOf(fileName) > -1) {
                files.add(item);
                // ファイルが見つかり次第終了する
                break;
            }

            if (item.isDirectory()) {
                for (File child : item.listFiles())
                    stack.push(child);
            }
        }

        return files;
    }
}
