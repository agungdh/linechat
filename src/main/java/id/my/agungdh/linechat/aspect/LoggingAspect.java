package id.my.agungdh.linechat.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Adjust the pointcut to target the layers you want to log.
    @Around("execution(* id.my.agungdh.linechat..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String loggingId = UUID.randomUUID().toString();
        MDC.put("loggingId", loggingId);

        // Construct the fully qualified method name
        String fullMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.debug("{} Entering: {} with arguments = {}", loggingId, fullMethodName, joinPoint.getArgs());

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;
            logger.debug("{} Exiting: {} with result = {} | Execution time: {} ms", loggingId, fullMethodName, result, executionTime);
            return result;
        } finally {
            MDC.remove("loggingId");
        }
    }
}
