package com.tcs.denmail.common.msg.exception;

public class MsgSystemException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MsgSystemException() {
  }

  public MsgSystemException(String message) {
    super(message);
  }

  public MsgSystemException(String message, Throwable cause) {
    super(message, cause);
  }

  public MsgSystemException(Throwable cause) {
    super(cause);
  }
}
