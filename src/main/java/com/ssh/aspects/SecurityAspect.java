package com.ssh.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class SecurityAspect {

    private Logger logger = Logger.getLogger(SecurityAspect.class.getName());

    @Around(value = "@annotation(ToLog)")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("*Security Aspect: Calling the intercepted method");

        Object returnedValue = joinPoint.proceed();
        logger.log(Level.INFO, "*Security Aspect: Method executed and returned {0}", returnedValue);

        return returnedValue;
    }
}
