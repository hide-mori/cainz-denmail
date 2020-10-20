package com.tcs.denmail.common.exception;

import com.tcs.denmail.common.msg.Msg;

public class TcsApplicationException extends Exception {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    public TcsApplicationException(String msgId, String... params) {
        super(Msg.getMessage(msgId, params));
    }

    public TcsApplicationException(String msgId, Throwable cause, String... params) {
        super(Msg.getMessage(msgId, params), cause);
    }
}
