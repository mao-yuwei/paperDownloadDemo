package com.pdl.gdkoala.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @description 日志统一打印
 * @author yangwei
 * 
 * @date 2019年9月25日-下午4:57:19
 */
//@Aspect
//@Component
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    ThreadLocal<Long> beginTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.pdl.gdkoala.controller..*.*(..)) "
    		+ "|| execution(public * com.pdl.gdkoala.service..*.*(..)) ")
    //  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void logInfo () {

    }

    @Before("logInfo ()")
    public void doBefore (JoinPoint joinPoint) throws Throwable {

        String pointLocation = joinPoint.toString();

        if (!StringUtils.contains(pointLocation, ".service.")) {
            beginTime.set(System.currentTimeMillis());

            Object[] args = joinPoint.getArgs();
            int pointCode = joinPoint.hashCode();

            // 记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            
            logger.info(pointCode + ":url: " + request.getRequestURL().toString());
            String requestBody = JSONObject.toJSONString(args).replace("[", "").replace("]", "");
			logger.info(pointCode + ":request_body: " + requestBody);
        }

    }

    @AfterReturning(returning = "response", pointcut = "logInfo ()")
    public void doAfterReturning (JoinPoint point, Object response) {

        String pointLocation = point.toString();

        if (!StringUtils.contains(pointLocation, ".service.")) {
            int pointCode = point.hashCode();

            // 记录返回内容
            long spendTime = System.currentTimeMillis() - beginTime.get();
            try {
                if (0 == spendTime) {
                    // 如果执行时间过短 睡眠1s 防止日志错乱
                    Thread.sleep(1L);
                }

                logger.info(pointCode + ":response_body: " + JSON.toJSONString(response));
                logger.info(pointCode + ":response_time: " + (spendTime));

            } catch (InterruptedException e) {
                logger.error("日志切面发生异常:", e);
            }
        }

    }

}
