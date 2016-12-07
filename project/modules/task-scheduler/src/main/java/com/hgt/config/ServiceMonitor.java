package com.hgt.config;

import com.hgt.common.constants.RestConst;
import com.hgt.domain.DataResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * 记录服务层方法参数,并对参数进行校验,同时监控方法执行时间
 *
 * @since: 15/11/21.
 * @author: yangjunming
 */
//定义切面
@Aspect
@Component
public class ServiceMonitor {

    private Logger logger = LoggerFactory.getLogger(ServiceMonitor.class);

//around在切入点前后切入内容
    @Around("execution(* com.hgt..*Controller.*(..))")
    public Object logServiceAccess(ProceedingJoinPoint pjp) {

        long start = System.currentTimeMillis();

        String className = pjp.getTarget().getClass().getName();
        String fullMethodName = className + "." + pjp.getSignature().getName();

        boolean needLog = false;

        Object result = null;
        try {
            result = pjp.proceed();
            if (result instanceof DataResult) {
                ((DataResult<?>) result).setIsSuccess(true);
            }
        } catch (Throwable e) {
            if (result != null && result instanceof DataResult) {
                DataResult<?> errorResult = (DataResult<?>) result;
                errorResult.setIsSuccess(false);
                if (StringUtils.isEmpty(errorResult.getErrorCode())) {
                    errorResult.setErrorCode(RestConst.ErrorCode.UNKNOWN);
                }
                if (StringUtils.isEmpty(errorResult.getErrorDesc())) {
                    errorResult.setErrorDesc(e.getLocalizedMessage());
                }
            } else {
                result = new DataResult<>(RestConst.ErrorCode.UNKNOWN, e.getMessage());
            }
            logger.error(fullMethodName + "执行出错,详情:", e);

            if (e instanceof DataAccessException) {
                //数据库层面的异常,继续向上抛,否则事务无法回滚
                ((DataResult<Object>) result).setErrorCode(RestConst.ErrorCode.DATABASE_ERROR);
            }
        }


        long end = System.currentTimeMillis();
        long elapsedMilliseconds = end - start;
        if (needLog) {
            logger.info(fullMethodName + "执行耗时:" + elapsedMilliseconds + " 毫秒");
        }

        if (result != null && result instanceof DataResult) {
            ((DataResult<?>) result).setElapsedMilliseconds(elapsedMilliseconds);
        }

        return result;
    }
}
