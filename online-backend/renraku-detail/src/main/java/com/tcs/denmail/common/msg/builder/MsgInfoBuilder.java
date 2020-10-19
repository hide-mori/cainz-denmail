package com.tcs.denmail.common.msg.builder;

import java.util.Map;
import com.tcs.denmail.common.msg.exception.MsgIdNotFoundException;
import com.tcs.denmail.common.msg.exception.MsgInitializeException;

public interface MsgInfoBuilder {
  void initialize() throws MsgInitializeException;

  Map getMsgInfo(String paramString) throws MsgIdNotFoundException;
}