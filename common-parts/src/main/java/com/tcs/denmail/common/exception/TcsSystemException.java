package com.tcs.denmail.common.exception;

import com.tcs.denmail.common.msg.Msg;

public class TcsSystemException extends RuntimeException {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    public TcsSystemException(String msgId, String... params) {
        super(Msg.getMessage(msgId, params));
    }

    public TcsSystemException(String msgId, Throwable cause, String... params) {
        super(Msg.getMessage(msgId, params), cause);
    }
}
