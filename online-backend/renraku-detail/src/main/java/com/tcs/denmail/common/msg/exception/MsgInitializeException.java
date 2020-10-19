package com.tcs.denmail.common.msg.exception;

public class MsgInitializeException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MsgInitializeException() {
  }

  public MsgInitializeException(String message) {
    super(message);
  }

  public MsgInitializeException(String message, Throwable cause) {
    super(message, cause);
  }

  public MsgInitializeException(Throwable cause) {
    super(cause);
  }
}
