package ke.co.dynamodigital.commons.services;

import org.springframework.messaging.Message;

import java.util.function.Predicate;

/**
 * @author Bibibiu
 * created 10/22/19 at 11:37
 **/
public interface MessageSender {

    /**
     * Defines how messages are going to be sent to dynamically
     * resolved destinations. The message is sent only if the
     * the predicate passes
     *
     * @param message     message to send
     * @param destination destination to resolve
     * @param send        predicate that tests whether to send the message
     * @param <T>         message type
     * @return true if message was sent successfully
     */
    <T> boolean send(Message<T> message, String destination, Predicate<Message<T>> send);


    /**
     * Sends message to dynamically resolved queue. Message is always
     * sent
     * @param message message to send
     * @param destination destination to resolve
     * @param <T> message type
     * @return true if message was sent successfully
     */
    <T> boolean send(Message<T> message, String destination);


}
