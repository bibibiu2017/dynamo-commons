package ke.co.dynamodigital.commons.stream;

import ke.co.dynamodigital.commons.models.message.DelayedMessageModel;
import org.springframework.messaging.Message;

import java.util.function.Predicate;

/**
 * @author Bibibiu
 * created 10/22/19 at 11:37
 **/
public interface MessageSender {
    String OUTPUT = "messageSender-out-0";

    /**
     * Sends a massage with provided payload to the
     * provided destination name which is dynamically
     * resolved when the send predicate passes
     *
     * @param <T>         payload type
     * @param message     message to send
     * @param destination destination to resolve
     * @param send        send message payload
     * @return true if message was sent successfully
     */
    <T> boolean send(Message<T> message, String destination, Predicate<Message<T>> send);


    /**
     * {@link #send(Message, String, Predicate) Sends} a message to provided
     * destination with send predicate always being true
     *
     * @param payload     message
     * @param destination destination
     * @param <T> message type
     * @return true if message has been sent successfully
     */
    <T> boolean send(Message<T> payload, String destination);

    /**
     * Creates a message from provided payload to the
     * procided destination {@link #send(Message, String) see}
     *
     * @param payload     message payload
     * @param destination destination
     * @param <T>         payload type
     * @return true if message has been sent
     */
    <T> boolean send(T payload, String destination);

    /**
     * Sends a message to a delayed exchange and after the delay the message is sent
     * to the provided return address. First the message is serialized to json before
     * being sent to the delay queue. The required headers are set from the object fields.
     * Some values have default values
     *
     * @param <T>                 message type
     * @param delayedMessageModel the message to delay
     *                            Headers put in message headers have a high priority to delayed message field values
     * @return true if message has been sent successfully
     * @see DelayedMessageModel
     */
    <T> boolean send(DelayedMessageModel<T> delayedMessageModel);

}
