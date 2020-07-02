package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.models.message.DelayedMessage;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import ke.co.dynamodigital.commons.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cloud.stream.function.StreamBridge;
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
@RequiredArgsConstructor
class MessageSenderImpl implements MessageSender {

    private final StreamBridge streamBridge;


    @Override
    public <T> boolean send(Message<T> message, String destination, Predicate<Message<T>> send) {
        if (send.test(message)) {
            logStreamSuccess(message, destination);
            return streamBridge.send(destination, message);
        } else {
            logStreamFailure(message, destination);
            return false;
        }
    }

    @Override
    public <T> boolean send(T payload, String destination, Predicate<T> send) {
        if (send.test(payload)) {
            return send(payload, destination);
        } else {
            logStreamFailure(AmqpUtils.buildMessageFrom(payload), destination);
            return false;
        }
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
    public <T> boolean send(@Valid DelayedMessage<T> delayedMessage) {
        //using Lombok val to ensure that these variables are effectively final
        val retries = delayedMessage.getRetires() == null ? 1 : delayedMessage.getRetires();
        val delay = delayedMessage.getDelay() == null ? 2000 : delayedMessage.getDelay();
        val address = delayedMessage.getAddress();
        val receivedHeaders = delayedMessage.getMessageHeaders() == null ? new MessageHeaders(null) : delayedMessage.getMessageHeaders();
        val headers = new HashMap<>(receivedHeaders) {{
            //Headers in provided message headers take president
            if (receivedHeaders.get(AmqpUtils.RETRIES_HEADER) == null) {
                put(AmqpUtils.RETRIES_HEADER, retries + 1);
            }
            if (receivedHeaders.get(AmqpUtils.DELAY_HEADER) == null) {
                put(AmqpUtils.DELAY_HEADER, delay);
            }
            if (receivedHeaders.get(AmqpUtils.RETURN_HEADER) == null) {
                put(AmqpUtils.RETURN_HEADER, address);
            }
        }};
        Message<?> message = AmqpUtils.buildMessageFrom(ObjectUtils.writeJson(delayedMessage.getPayload()), headers);
        return send(message, ParkingStream.OUTPUT);
    }

    private void logStreamSuccess(Message<?> message, String destination) {
        if (log.isInfoEnabled()) {
            log.info("\n======================================================" +
                    "\nMessage sender send condition passed" +
                    "\nAttempting to Send Message To: {}" +
                    "\n======================================================", destination);
        }
        if (log.isDebugEnabled()) {
            log.debug("\n======================================================" +
                    "\nMessage sender send condition passed" +
                    "\nAttempting to Send Message:{}" +
                    "\n To: {}" +
                    "\n======================================================", ObjectUtils.writeJson(message.getPayload()), destination);
        }
        if (log.isTraceEnabled()) {
            log.debug("\n======================================================" +
                    "\nMessage sender send condition passed" +
                    "\nAttempting to Send Message:{}" +
                    "\n To: {}" +
                    "\n======================================================", ObjectUtils.writeJson(message), destination);
        }
    }

    private void logStreamFailure(Message<?> message, String destination) {
        if (log.isInfoEnabled()) {
            log.info("\n=========================================================" +
                    "\nMessage sender send condition failed" +
                    "\nWill not send message To: {}" +
                    "\n===========================================================", destination);
        }
        if (log.isDebugEnabled()) {
            log.debug("\n=========================================================" +
                    "\nMessage sender send condition failed" +
                    "\nWill not send message: {}" +
                    "\nTo: {}" +
                    "\n===========================================================", ObjectUtils.writeJson(message.getPayload()), destination);
        }
        if (log.isTraceEnabled()) {
            log.debug("\n=========================================================" +
                    "\nMessage sender send condition failed" +
                    "\nWill not send message: {}" +
                    "\nTo: {}" +
                    "\n===========================================================", ObjectUtils.writeJson(message), destination);
        }
    }
}
