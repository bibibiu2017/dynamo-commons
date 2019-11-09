package ke.co.dynamodigital.commons.services;

import ke.co.dynamodigital.commons.dtos.general.DelayedMessageDTO;
import ke.co.dynamodigital.commons.stream.parking.ParkingSource;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.function.Predicate;

import static ke.co.dynamodigital.commons.utils.AmqpUtils.*;
import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;

/**
 * @author Bibibiu
 * created 10/22/19 at 11:42
 **/
@Data
@Slf4j
@SuperBuilder(toBuilder = true)
public class MessageSenderImpl implements MessageSender {

    private BinderAwareChannelResolver resolver;

    @Override
    public <T> boolean send(Message<T> message, String destination, Predicate<Message<T>> send) {
        if (send.test(message)) {
            log.info("\n=======================================" +
                    "\nsend condition passed will send message" +
                    "\n=======================================");
            return resolver.resolveDestination(destination).send(message);
        }
        log.info("\n==========================================" +
                "\nsend condition failed will not send message" +
                "\n==========================================");
        return false;
    }

    @Override
    public <T> boolean send(Message<T> message, String destination) {

        return send(message, destination, tMessage -> true);
    }

    @Override
    public boolean send(Object messageObject, String destination) {
        return send(AmqpUtils.buildMessageFrom(messageObject), destination);
    }

    @Override
    public <T> boolean sendDelayed(@Valid DelayedMessageDTO<T> delayedMessage) {
        //using Lombok val to ensure that these variables are effectively final
        val retries = delayedMessage.getRetires() == null ? 1 : delayedMessage.getRetires();
        val delay = delayedMessage.getDelay() == null ? 2000 : delayedMessage.getDelay();
        val address = delayedMessage.getAddress();
        val receivedHeaders = delayedMessage.getMessageHeaders() == null ? new MessageHeaders(null) : delayedMessage.getMessageHeaders();
        val headers = new HashMap<String, Object>(receivedHeaders) {{
            //Headers in provided message headers take president
            if (receivedHeaders.get(RETRIES_HEADER) == null) {
                put(RETRIES_HEADER, retries);
            }
            if (receivedHeaders.get(DELAY_HEADER) == null) {
                put(DELAY_HEADER, delay);
            }
            if (receivedHeaders.get(RETURN_HEADER) == null) {
                put(RETURN_HEADER, address);
            }
        }};
        Message<?> message = AmqpUtils.buildMessageFrom(writeJson(delayedMessage.getPayload()), headers);

        return send(message, ParkingSource.OUTPUT);
    }
}
