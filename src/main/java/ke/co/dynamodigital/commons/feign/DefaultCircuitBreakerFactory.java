package ke.co.dynamodigital.commons.feign;

import ke.co.dynamodigital.commons.error.GenericHttpException;
import ke.co.dynamodigital.commons.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.*;

import java.util.function.*;

/**
 * @author arthurmita
 * created 23/05/2021 at 14:34
**/
@Slf4j
public class DefaultCircuitBreakerFactory extends CircuitBreakerFactory {

    @Override
    public CircuitBreaker create(String id) {
        return new CircuitBreaker() {
            @Override
            public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
                try {
                    return toRun.get();
                } catch (Exception e) {
                    if (e instanceof GenericHttpException)
                        throw ((GenericHttpException) e);
                    LogUtils.logObject(log, id, "Circuit Breaker Trip");
                    return fallback.apply(e);
                }
            }
        };
    }

    @Override
    protected ConfigBuilder configBuilder(String id) {
        return Object::new;
    }

    @Override
    public void configureDefault(Function defaultConfiguration) {

    }
}
