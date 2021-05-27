package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.utils.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.*;
import org.springframework.messaging.*;

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
    static final String INPUT = "parking-in-0";
    public static final String OUTPUT = "parking-out-0";

    /**
     * <p>Delayed exchange default queue destination that sends the message to provided
     * destination in the header. This queue expects any generic message logs the
     * events and return the message to the provided return address</p>
     * <p><strong>Note:</strong> Message payload must be serialized to jason before sending
     * it to this queue</p>
     *
     * @param messageSender message sender
     * @return The stream {@link Consumer} that will process this
     */
    @Bean
    @ConditionalOnClass({Message.class, MessageSender.class})
    public Consumer<Message<byte[]>> parking(final MessageSender messageSender) {
        return message -> {
            MessageHeaders headers = message.getHeaders();
            Integer retries = (Integer) headers.getOrDefault(AmqpUtils.RETRIES_HEADER, 1);
            Integer delay = (Integer) headers.getOrDefault(AmqpUtils.AMQP_DELAY_HEADER, headers.get(AmqpUtils.DELAY_HEADER));
            String address = headers.get(AmqpUtils.RETURN_HEADER, String.class);

            log.trace("Parked message: {}", ObjectUtils.writeJson(message));
            log.info("\n=======================================" +
                    "\n Receiving Delayed Message" +
                    "\nHoldFor: {}ms" +
                    "\nDelayed: {} {}" +
                    "\nSendTo: {}" +
                    "\n=======================================", delay, retries, retries == 1 ? "time" : "times", address);
            val headersToReturn = new HashMap<>(headers);
            headersToReturn.put(AmqpUtils.RETRIES_HEADER, (retries + 1));
            messageSender.send(AmqpUtils.buildMessageFrom(message.getPayload(), headersToReturn), address);
        };
    }
}
