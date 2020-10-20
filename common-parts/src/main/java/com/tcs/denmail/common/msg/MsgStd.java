package com.tcs.denmail.common.msg;

public final class MsgStd {
  public static void err(String content) {
    err(content, null);
  }

  public static void err(Throwable e) {
    err(e.getMessage(), e);
  }

  public static void err(String content, Throwable e) {
    System.err.println(content);
    if (e != null) {
      e.printStackTrace();
    }
  }

  public static void out(String content) {
    System.out.println(content);
  }
}
