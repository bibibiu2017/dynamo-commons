package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.models.message.DelayedMessageModel;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import ke.co.dynamodigital.commons.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * @author Bibibiu
 * created 10/22/19 at 11:42
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {

    @Autowired
    private final StreamBridge streamBridge;


    @Override
    public <T> boolean send(Message<T> message, String destination, Predicate<Message<T>> send) {
        if (send.test(message)) {
            T payload = message.getPayload();
            log.info("\n===================================" +
                    "\nMessage sender send condition passed" +
                    "\nSending Message: {}" +
                    "\nTo: {}" +
                    "\n===================================", ObjectUtils.writeJson(payload), destination);
            return streamBridge.send(destination, message);
        }
        log.info("\n===================================" +
                "\nMessage sender send condition failed" +
                "\nWill not send message" +
                "=====================================");
        return false;
    }


    @Override
    public <T> boolean send(Message<T> message, String destination) {
        return send(message, destination, send -> true);
    }

    @Override
    public <T> boolean send(T payload, String destination) {
        return send(AmqpUtils.buildMessageFrom(payload), destination);
    }

    @Override
    public <T> boolean send(@Valid DelayedMessageModel<T> delayedMessageModel) {
        //using Lombok val to ensure that these variables are effectively final
        val retries = delayedMessageModel.getRetires() == null ? 1 : delayedMessageModel.getRetires();
        val delay = delayedMessageModel.getDelay() == null ? 2000 : delayedMessageModel.getDelay();
        val address = delayedMessageModel.getAddress();
        val receivedHeaders = delayedMessageModel.getMessageHeaders() == null ? new MessageHeaders(null) : delayedMessageModel.getMessageHeaders();
        val headers = new HashMap<>(receivedHeaders) {{
            //Headers in provided message headers take president
            if (receivedHeaders.get(AmqpUtils.RETRIES_HEADER) == null) {
                put(AmqpUtils.RETRIES_HEADER, retries);
            }
            if (receivedHeaders.get(AmqpUtils.DELAY_HEADER) == null) {
                put(AmqpUtils.DELAY_HEADER, delay);
            }
            if (receivedHeaders.get(AmqpUtils.RETURN_HEADER) == null) {
                put(AmqpUtils.RETURN_HEADER, address);
            }
        }};
        Message<?> message = AmqpUtils.buildMessageFrom(ObjectUtils.writeJson(delayedMessageModel.getPayload()), headers);
        return send(message, ParkingStream.INPUT);
    }
}
