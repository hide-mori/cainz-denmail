package com.tcs.denmail.common.util;

import java.util.HashMap;
import java.util.Map;

import com.tcs.denmail.common.msg.Msg;
import com.tcs.denmail.common.msg.MsgConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    protected final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private final static String LOG_LEVEL_TRACE = "TRACE";
    private final static String LOG_LEVEL_DEBUG = "DEBUG";
    private final static String LOG_LEVEL_INFO = "INFO";
    private final static String LOG_LEVEL_WARN = "WARN";
    private final static String LOG_LEVEL_ERROR = "ERROR";
    private final static String LOG_LEVEL_FATAL = "FATAL";

    /**
     * ログ出力. メッセージ取得部品から取得したメッセージ情報に応じてログを出力する.
     * 
     * @param msgId     メッセージID
     * @param paramList メッセージパラメータ
     */
    public static void log(String msgId, String... paramList) {
        Map<String, Object> msgInfoMap = (HashMap<String, Object>) Msg.getMsgInfo(msgId);
        String level = (String) msgInfoMap.get(MsgConstants.KEY_LEVEL);
        String message = Msg.getMessage(msgId, paramList);
        switch (level) {
            case LOG_LEVEL_TRACE:
                logger.trace(message);
                break;
            case LOG_LEVEL_DEBUG:
                logger.debug(message);
                break;
            case LOG_LEVEL_INFO:
                logger.info(message);
                break;
            case LOG_LEVEL_WARN:
                logger.warn(message);
                break;
            case LOG_LEVEL_ERROR:
                logger.error(message);
                break;
            case LOG_LEVEL_FATAL:
                logger.error(message);
                break;
            default:
                logger.error(message);
                break;
        }
    }
}