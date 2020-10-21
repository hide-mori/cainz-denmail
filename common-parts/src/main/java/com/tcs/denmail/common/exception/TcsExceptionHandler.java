package com.tcs.denmail.common.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import com.tcs.denmail.common.util.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TcsExceptionHandler {

    @ExceptionHandler({TcsApplicationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleApplicationError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "アプリケーションエラー");
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        return errorMap;
    }

    @ExceptionHandler({TcsSystemException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleSystemError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "システムエラー");
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorMap;
    }

    @ExceptionHandler({TransactionSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleTransactionSystemException(
            TransactionSystemException exception) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "バリデーションエラー");
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());

        Throwable rootCause = exception.getRootCause();
        if (rootCause instanceof ConstraintViolationException) {
            ConstraintViolationException ce = ((ConstraintViolationException) rootCause);

            Object target = ce.getConstraintViolations().stream().map(e -> e.getRootBean())
                    .findFirst().orElse(null);
            LogUtil.log("DM0003E", target.toString(), ce.getMessage());

            errorMap.put("entity", target.toString());
            errorMap.put("errorDetail", ce.getMessage());
        }

        return errorMap;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleException(Exception exception) {
        exception.printStackTrace();
        Map<String, Object> errorMap = new LinkedHashMap<String, Object>();
        errorMap.put("message", "予期せぬエラー");
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorMap;
    }
}
