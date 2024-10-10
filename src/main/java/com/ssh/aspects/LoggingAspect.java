package com.ssh.aspects;

import java.util.logging.Logger;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ssh.model.Comment;
import java.util.logging.Level;

@Component
@Aspect
@Order(2)
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    //@Around("execution(* com.ssh.services.*.*(..))")
    @Around("@annotation(ToLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        logger.info(">>LoggingAspect.Method: " + methodName + " with parameters " + Arrays.asList(arguments) + " will execute");
        //Change the parameter value
        Comment comment = new Comment();
        comment.setText("Some other text!");
        Object[] newArguments = {comment};

        Object returnedByMethod = joinPoint.proceed(newArguments);
        logger.log(Level.INFO, ">LoggingAspect.Method executed and returned: {0}", returnedByMethod);
        //return returnedByMethod;
        return "FAILED";
    }

}
