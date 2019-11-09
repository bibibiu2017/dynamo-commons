package ke.co.dynamodigital.commons.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author arthurmita
 * created 31/10/2019 at 22:04
 **/
@Slf4j
@Aspect
@Component
public class LogServiceAspect {

    @Pointcut("target(ke.co.dynamodigital.commons.services.LogService)")
    public void logServiceMethods() {}

    @Pointcut("@annotation(ke.co.dynamodigital.commons.annotations.EnableLogService)")
    public void logServiceEnabledMethods() {}


}
