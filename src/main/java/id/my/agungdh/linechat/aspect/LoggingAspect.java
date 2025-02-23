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
//    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    @Around("execution(* id.my.agungdh.linechat..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        // Generate a unique log ID for this execution thread
        String loggingId = UUID.randomUUID().toString();
        MDC.put("loggingId", loggingId);

        // Log the "Entering" message with the logging ID prefixed
        logger.debug("{} Entering: {} with arguments = {}",
                loggingId,
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs()));

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;

            // Log the "Exiting" message with the logging ID and execution time
            logger.debug("{} Exiting: {} with result = {} | Execution time: {} ms",
                    loggingId,
                    joinPoint.getSignature().toShortString(),
                    result,
                    executionTime);
            return result;
        } finally {
            // Always remove the logging ID from MDC to avoid leaking it across threads
            MDC.remove("loggingId");
        }
    }
}
