package com.tcs.denmail.common.msg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MsgProperty {
  private static Properties property = null;

  public static void startup() {
    initialize("msg.properties");

    Msg.initialize();
  }

  public static void startup(String propertyPath) {
    initialize(propertyPath);

    Msg.initialize();
  }

  private static void initialize(String propertyPath) {
    InputStream in = null;

    try {
      property = new Properties();
      in = MsgProperty.class.getClassLoader().getResourceAsStream(propertyPath);
      property.load(in);
    } catch (IOException e) {

      MsgStd.err("MsgProperty#startup(" + propertyPath + ")" + ":読込に失敗しました。");
    } catch (NullPointerException e) {

      MsgStd.err("MsgProperty#startup(" + propertyPath + ")" + ":読込に失敗しました。");
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {

          MsgStd.err("MsgProperty#startup:ファイルクローズに失敗しました。");
        }
      }
    }
  }

  public static String getProperty(String paramName) {
    String paramValue = null;

    if (property != null) {
      paramValue = property.getProperty(paramName);
    }
    return paramValue;
  }
}
