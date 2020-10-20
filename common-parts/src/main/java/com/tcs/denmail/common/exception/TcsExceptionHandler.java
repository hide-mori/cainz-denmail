package com.tcs.denmail.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TcsExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({ TcsApplicationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleApplicationError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "アプリケーションエラー");
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        return errorMap;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ TcsSystemException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleSystemError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "システムエラー");
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorMap;
    }
}