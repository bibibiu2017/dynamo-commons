package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.utils.AmqpUtils;
import ke.co.dynamodigital.commons.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @author Bibibiu
 * created 9/7/19 at 02:24
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ParkingStream {
    final private MessageSender messageSender;

    public static final String INPUT = "parking-in-0";

    /**
     * <p>Delayed exchange default queue destination that sends the message to provided
     * destination in the header. This queue expects any generic message logs the
     * events and return the message to the provided return address</p>
     * <p><strong>Note:</strong> Message payload must be serialized to jason before sending
     * it to this queue</p>
     *
     * @return The stream {@link Consumer} that will process this
     */
    @Bean
    public Consumer<Message<byte[]>> parking() {
        return message -> {
            MessageHeaders headers = message.getHeaders();
            Integer retries = headers.get(AmqpUtils.RETRIES_HEADER, Integer.class);
            Integer delay = headers.get("amqp_receivedDelay", Integer.class);
            String address = headers.get(AmqpUtils.RETURN_HEADER, String.class);
            val delayedPayload = message.getPayload();
            log.trace("Parked message: {}", ObjectUtils.writeJson(message));
            log.info("\n=======================================" +
                    "\nParkedMessage: {}" +
                    "\nDuration: {}" +
                    "\nParkedTimes: {}" +
                    "\nReturnAddress: {}" +
                    "\n=======================================", ObjectUtils.writeJson(ObjectUtils.readJson(new String(delayedPayload), Object.class)), delay, retries, address);
            val headersToReturn = new HashMap<>(headers);
            if (retries == null) {
                retries = 1;
            }
            headersToReturn.put(AmqpUtils.RETRIES_HEADER, (retries + 1));
            messageSender.send(AmqpUtils.buildMessageFrom(message.getPayload(), headersToReturn), address);
        };
    }
}
