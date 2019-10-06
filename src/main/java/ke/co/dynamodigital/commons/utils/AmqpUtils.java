package ke.co.dynamodigital.commons.utils;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.Map;

/**
 * @author Bibibiu
 * created 8/28/19 at 23:46
 **/
public class AmqpUtils {

    public static Long getDeadLetterCount(Map<?,?> deathHeader) {
        return (Long) deathHeader.get("count");
    }

    public static <T> Message<T> buildMessageFrom(T var) {
        return MessageBuilder.withPayload(var).build();
    }

    public static <T> Message<T> buildMessageFrom(T var, Map<String, Object> headers) {
        return MessageBuilder
                .withPayload(var)
                .copyHeaders(headers)
                .build();
    }

    public static <T> Message<T> buildMessageFrom(T var, String headerName, Object header) {
        return MessageBuilder
                .withPayload(var)
                .setHeaderIfAbsent(headerName,header)
                .build();
    }

    public Integer deathCount(Map<?,?> death) {
        return ((Long) death.get("count")).intValue();
    }
}
