package ke.co.dynamodigital.commons.utils;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.Map;

/**
 * @author Bibibiu
 * created 8/28/19 at 23:46
 * Helper methods for AMQP
 **/
public class AmqpUtils {

    /**
     * Gets the death count. The number of times a message has been dead lettered
     *
     * @param deathHeader Header with death information
     * @return number of times a message has been dead lettered
     */
    public static Long getDeadLetterCount(Map<?, ?> deathHeader) {
        return (Long) deathHeader.get("count");
    }

    /**
     * Builds a message and sets the payload as the supplied objectt
     *
     * @param var object to set as message payload
     * @param <T> Class type of payload
     * @return message with payload set
     */
    public static <T> Message<T> buildMessageFrom(T var) {
        return MessageBuilder.withPayload(var).build();
    }

    /**
     * Copy's headers to the message object.
     *
     * @param var     message payload
     * @param headers headers to copy
     * @param <T>     class type of payload
     * @return message with payload set and headers copied
     * @see #buildMessageFrom(Object)
     */
    public static <T> Message<T> buildMessageFrom(T var, Map<String, Object> headers) {
        return MessageBuilder
                .withPayload(var)
                .copyHeaders(headers)
                .build();
    }

    /**
     * Sets a header object if the header is not present.
     *
     * @param var        message payload
     * @param headerName header name
     * @param header     header object
     * @param <T>        class type of payload
     * @return message with payload set and header added
     * @see #buildMessageFrom(Object)
     */
    public static <T> Message<T> buildMessageFrom(T var, String headerName, Object header) {
        return MessageBuilder
                .withPayload(var)
                .setHeaderIfAbsent(headerName, header)
                .build();
    }

    /**
     * Calculates the delay time of a message that is to be
     * sent to a delayed exchange. Exponential back of is
     * provided here. The minimum delay is 2000 ms and the
     * maximum delay is 1 024 000 ms
     *
     * @param retries number of retries for a message
     * @return delay millis
     */
    public static Integer calculateDelay(int retries) {
        //noinspection WrapperTypeMayBePrimitive
        Double secs = Math.pow(2, (retries % 10));
        return 1000 * secs.intValue();
    }
}
