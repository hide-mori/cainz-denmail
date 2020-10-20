package com.tcs.denmail.common.msg.builder;

import com.tcs.denmail.common.msg.MsgProperty;
import com.tcs.denmail.common.msg.MsgStd;
import com.tcs.denmail.common.msg.exception.MsgInitializeException;

public class MsgInfoBuilderFactory {
  public static MsgInfoBuilder getMsgInfoBuilder() throws MsgInitializeException {
    try {
      String msgInfoBuilderName = MsgProperty.getProperty("MSG_INFO_BUILDER_NAME");

      Class<?> msgInfoBuilderClass = Class.forName(msgInfoBuilderName);
      MsgInfoBuilder builder = (MsgInfoBuilder) msgInfoBuilderClass.newInstance();

      if (builder == null) {
        throw new Exception();
      }
      return builder;
    } catch (Exception e) {
      MsgStd.err(e);
      throw new MsgInitializeException("メッセージ情報ファイルの解析中にエラーが発生しました。");
    }
  }
}
