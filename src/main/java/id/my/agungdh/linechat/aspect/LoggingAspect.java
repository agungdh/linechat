package id.my.agungdh.linechat.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Adjust the pointcut expression to target your specific packages or methods
    @Around("execution(* id.my.agungdh.linechat..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // Log method entry along with arguments
        logger.info("Entering: {}() with arguments = {}",
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();  // Proceed with the original method call

        long elapsedTime = System.currentTimeMillis() - startTime;
        // Log method exit along with result and execution time
        logger.info("Exiting: {}() with result = {} | Execution time: {} ms",
                joinPoint.getSignature().getName(), result, elapsedTime);

        return result;
    }
}
