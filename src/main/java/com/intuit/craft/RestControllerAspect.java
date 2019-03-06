/**
 * 
 */
package com.intuit.craft;

/**
 * @author nicky
 *
 */
import static org.slf4j.LoggerFactory.getLogger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestControllerAspect {

	private static final Class<RestControllerAspect> aspectClass = RestControllerAspect.class;
	 private static final Logger LOGGER = getLogger(aspectClass);
	 
    @Before("execution(public * com.intuit.craft.controllers.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        LOGGER.info("<<<Logging call>>>" + pjp);
    }
}

