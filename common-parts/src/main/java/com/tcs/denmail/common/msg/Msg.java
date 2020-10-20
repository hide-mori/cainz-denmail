package com.tcs.denmail.common.msg;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import com.tcs.denmail.common.msg.builder.MsgInfoBuilder;
import com.tcs.denmail.common.msg.builder.MsgInfoBuilderFactory;
import com.tcs.denmail.common.msg.exception.MsgIdNotFoundException;
import com.tcs.denmail.common.msg.exception.MsgInitializeException;

public class Msg {
  private static MsgInfoBuilder msgInfoBuilder;

  public static String getMessage(String msgId) {
    return getMessage(msgId, null, Locale.getDefault());
  }

  public static String getMessage(String msgId, String... paramList) {
    return getMessage(msgId, paramList, Locale.getDefault());
  }

  public static String getMessage(String msgId, String[] paramList, Locale locale) {
    try {
      Map msgInfo = getMsgInfo(msgId);
      Map<String, String> contents = (Map<String, String>) msgInfo.get("content");
      String content = getContent(contents, locale);
      return formatContent(content, paramList);
    } catch (MsgIdNotFoundException e) {
      MsgStd.err((Throwable) e);
      return "";
    }
  }

  private static String getContent(Map<String, String> contents, Locale locale) {
    if (contents == null || locale == null) {
      return "";
    }

    String[] keys = { locale.toString(), locale.getLanguage() + "_" + locale.getCountry(), locale.getLanguage(), "" };

    for (String key : keys) {
      if (contents.containsKey(key)) {
        return contents.get(key);
      }
    }

    return "";
  }

  public static Map getMsgInfo(String msgId) throws MsgIdNotFoundException, MsgInitializeException {
    if (msgInfoBuilder == null) {
      initialize();
    }
    return msgInfoBuilder.getMsgInfo(msgId);
  }

  public static String formatContent(String content, String[] paramList) {
    if (paramList == null) {
      return deleteParamSymbol(content);
    }
    for (int i = 0; i < paramList.length; i++) {

      if (paramList[i] == null) {
        paramList[i] = "";
      }
    }

    if (content == null) {
      content = "";
    } else {
      content = MessageFormat.format(content.replaceAll("'", "''"), (Object[]) paramList);
    }
    return deleteParamSymbol(content);
  }

  private static String deleteParamSymbol(String content) {
    content = content.replaceAll(MsgProperty.getProperty("REGEX_PARAM_MARK"), "");
    return content;
  }

  public static void initialize() throws MsgInitializeException {
    msgInfoBuilder = MsgInfoBuilderFactory.getMsgInfoBuilder();

    msgInfoBuilder.initialize();
  }
}
