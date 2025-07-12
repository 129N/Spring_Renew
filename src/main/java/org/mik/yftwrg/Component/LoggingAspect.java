package org.mik.yftwrg.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@RequiredArgsConstructor
@Log4j2
public class LoggingAspect {
        @Before("@annotation(org.mik.yftwrg.annotation.LogInfo)")
    public void logInfo(JoinPoint joinPoint) {
        log.info("Entering method: {} with args: {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs()));
    }



}


//    @Pointcut("within(hu.innobyte.innocc.agentdataservice..*)")
//    public void methodMeasured() {}
//
//    @Around(value = "hu.innobyte.inocc.agentdataservice.component.LoggingAsppect.methodMeasured()")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        Object proceed = joinPoint.proceed();
//        log.info("{} ececuted in {} ms", joinPoint.getSignature(), executionTime);
//        return  proceed;
//    }
//
//    @Before("@annotation(hu.innobyte.inocc.agentdataservice.annotation.LogInfo)")
//    public void logInfo(JoinPoint joinPoint) {
//        log.info("Entering method: {} with args: {}",
//                Arrays.toString(joinPoint.getArgs())
//                );
//    }