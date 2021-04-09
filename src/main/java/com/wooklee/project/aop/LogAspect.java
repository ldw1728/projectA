package com.wooklee.project.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class LogAspect {

	
	@Around("execution(* com.wooklee.project.controller.*Controller.*(..))")
	public Object loggingController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		
		Object result = proceedingJoinPoint.proceed();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		String methodName = proceedingJoinPoint.getSignature().getName();
		String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
		
		log.info("==================================================================================");
		log.info("[ " + controllerName + " - " + methodName + " ]" + "	URI : " + request.getRequestURI() + "  ,  Method : " + request.getMethod());
		log.info("==================================================================================");
		
		
		return result;
	}
}
