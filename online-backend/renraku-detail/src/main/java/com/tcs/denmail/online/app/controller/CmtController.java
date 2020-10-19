package com.tcs.denmail.online.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.common.exception.TcsApplicationException;
import com.tcs.denmail.common.exception.TcsSystemException;
import com.tcs.denmail.common.msg.Msg;
import com.tcs.denmail.common.session.TcsSession;
import com.tcs.denmail.common.util.DateUtil;
import com.tcs.denmail.common.util.LogUtil;
import com.tcs.denmail.common.util.PropertyUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CmtController
 */
@RestController
@ComponentScan("com.tcs.denmail.common")
public class CmtController extends TcsBaseController {

    @Autowired
    private TcsSession tcsSession;

    @GetMapping(value = "/cmt")
    public String cmt() throws TcsApplicationException {
        String result = null;
        String sessionKey = "serverDate";
        tcsSession.setAttribute(sessionKey, DateUtil.getDatetimeString(DateUtil.getDatetime(), "yyyy/MM/dd hh:ss.SSS"));
        String systemDate = (String) tcsSession.getAttribute(sessionKey);
        String systemName = PropertyUtil.getProperty("systemName");

        result = systemDate + " " + systemName;

        return result;
    }

    @GetMapping(value = "/appex")
    public String appex() throws TcsApplicationException {
        throw new TcsApplicationException("DM0003E");
    }

    @GetMapping(value = "/sysex")
    public String sysex() throws TcsSystemException {
        throw new TcsSystemException("DM0004F", new Throwable());
    }

    @GetMapping(value = "/msg")
    public List<String> msg() throws TcsApplicationException {
        List<String> result = new ArrayList<String>();
        result.add(Msg.getMessage("DM0001E"));
        result.add(Msg.getMessage("DM0002E", new String[] { "12345", "67890" }));
        return result;
    }

    @GetMapping(value = "/msglog")
    public String msgInfo() throws TcsApplicationException {
        LogUtil.log("DM0001E");
        LogUtil.log("DM0002E", new String[] { "12345", "67890" });
        return "ログファイルを確認してください";
    }
}