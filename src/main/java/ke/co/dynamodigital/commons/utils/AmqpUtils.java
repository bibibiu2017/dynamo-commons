package ke.co.dynamodigital.commons.utils;

import lombok.experimental.UtilityClass;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.Map;

/**
 * @author Bibibiu
 * created 8/28/19 at 23:46
 * Helper methods for AMQP
 **/
@UtilityClass
public class AmqpUtils {

    /**
     * Number of times a message has been sent to the parking queue
     */
    public final String RETRIES_HEADER = "x-retries";

    /**
     * Number of milliseconds to delay message
     */
    public final String DELAY_HEADER = "x-delay";

    /**
     * Name of queue to send message to after delay
     */
    public final String RETURN_HEADER = "x-return";

    /**
     * Send to header spring functions
     */
    public final String SEND_TO_HEADER = "spring.cloud.stream.sendto.destination";

    /**
     * Death header
     */
    public final String DEATH_HEADER = "x-death";

    /**
     * AMQP delay header
     */
    public final String AMQP_DELAY_HEADER = "amqp_receivedDelay";

    /**
     * Gets the death count. The number of times a message has been dead lettered
     *
     * @param deathHeader Header with death information
     * @return number of times a message has been dead lettered
     */
    public Long getDeadLetterCount(Object deathHeader) {
        Map<?, ?> death;
        if (deathHeader instanceof Map) {
            death = (Map<?, ?>) deathHeader;
        } else {
            death = (Map<?, ?>) ((List<?>) deathHeader).get(0);
        }
        return (Long) death.get("count");
    }

    /**
     * Builds a message and sets the payload as the supplied objectt
     *
     * @param var object to set as message payload
     * @param <T> Class type of payload
     * @return message with payload set
     */
    public <T> Message<T> buildMessageFrom(T var) {
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
    public <T> Message<T> buildMessageFrom(T var, Map<String, Object> headers) {
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
    public <T> Message<T> buildMessageFrom(T var, String headerName, Object header) {
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
    @Deprecated(since = "1.0.0-SNAPSHOT", forRemoval = true)
    public Integer calculateDelay(int retries) {
        //noinspection WrapperTypeMayBePrimitive
        Double secs = Math.pow(2, (retries % 10));
        return 1000 * secs.intValue();
    }

    /**
     * Calculate message delay based on the current failed attempts to process the message
     * and a given maximum delay
     *
     * @param failedAttempts retries counter
     * @param maxDelay       maximum delay
     * @return calculated delay
     */
    public long exponentialDelay(long failedAttempts, long maxDelay) {
        Double delay = ((1d / 2d) * (Math.pow(2d, failedAttempts) - 1d));
        return Math.min(delay.longValue(), maxDelay);
    }
}
