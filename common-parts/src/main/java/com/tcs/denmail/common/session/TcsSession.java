package com.tcs.denmail.common.session;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TcsSession {

    /**
     * HTTPセッション.
     */
    @Autowired
    private HttpSession session;

    /**
     * セッションに値を設定する.
     * 
     * @param key   キー名
     * @param value データ
     */
    public void setAttribute(final String key, final Object value) {
        // セッションへ保存
        session.setAttribute(key, value);
    }

    /**
     * セッションから値を取得する。
     * 
     * @param key キー名
     * @return データ
     */
    public Object getAttribute(final String key) {
        return session.getAttribute(key);
    }

    public void invalidate() {
        // セッションクリア
        session.invalidate();
    }
}