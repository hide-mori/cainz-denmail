package com.tcs.denmail.common.msg.exception;

public class MsgIdNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MsgIdNotFoundException(String msgId) {
    super("指定されたメッセージIDは存在しません。(メッセージID：" + msgId + ")");
  }
}
