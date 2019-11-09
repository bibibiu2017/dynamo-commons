package ke.co.dynamodigital.commons.services;

import ke.co.dynamodigital.commons.dtos.general.DelayedMessageDTO;
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

    /**
     * Creates message and sends it to a dynamically resolved queue
     * @param messageObject message object used to create message of type {@code T}
     * @param destination destination to send message
     * @see #send(Message, String)
     * @return true if message has been sent successfully
     */
    boolean send(Object messageObject, String destination);

    /**
     * Sends a message to a delayed exchange and after the delay the message is sent
     * to the provided return address. First the message is serialized to json before
     * being sent to the delay queue. The required headers are set from the object fields.
     * Some values have default values
     * @param <T> message type
     * @see DelayedMessageDTO
     * @see ke.co.dynamodigital.commons.stream.parking.ListenersParking#park(Message, Integer, Integer, String)
     * @apiNote Headers put in message headers have a high priority to delayed message field values
     * @return true if message has been sent successfully
     */
    <T>boolean sendDelayed(DelayedMessageDTO<T> delayedMessage);

}
