package com.ssh.aspects;

import java.util.logging.Logger;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import com.ssh.model.Comment;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* com.ssh.services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        logger.info("Method: " + methodName + " with parameters " + Arrays.asList(arguments) + " will execute");
        //Change the parameter value
        Comment comment = new Comment();
        comment.setText("Some other text!");
        Object[] newArguments = {comment};

        Object returnedByMethod = joinPoint.proceed(newArguments);
        logger.info("Method executed and returned: " + returnedByMethod);
        //return returnedByMethod;
        return "FAILED";
    }

}
