package com.tcs.denmail.common.interceptors;

import com.tcs.denmail.common.exception.TcsApplicationException;
import com.tcs.denmail.common.util.LogUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TcsControllerInterceptor {

    /**
     * メソッド実行前処理.
     * 
     * @param joinPoint
     * @throws TcsApplicationException
     */
    @Before("execution(* com.tcs.denmail..*Controller.*(..))")
    public void beforeLog(JoinPoint joinPoint) throws TcsApplicationException {
        LogUtil.log("DM0001D", new String[] { makeClassMethodName(joinPoint) });
    }

    /**
     * メソッド実行前処理.
     * 
     * @param joinPoint
     * @throws TcsApplicationException
     */
    @After("execution(* com.tcs.denmail..*Controller.*(..))")
    public void afterLog(JoinPoint joinPoint) throws TcsApplicationException {
        LogUtil.log("DM0002D", new String[] { makeClassMethodName(joinPoint) });
    }

    private String makeClassMethodName(JoinPoint joinPoint) {
        StringBuilder classMethodNameBuiler = new StringBuilder(joinPoint.getTarget().getClass().toString())
                .append(".");
        classMethodNameBuiler.append(joinPoint.getSignature().getName());
        String classMethodName = classMethodNameBuiler.toString();

        return classMethodName;
    }
}
