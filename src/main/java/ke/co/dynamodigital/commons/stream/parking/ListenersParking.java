package ke.co.dynamodigital.commons.stream.parking;

import ke.co.dynamodigital.commons.services.MessageSender;
import ke.co.dynamodigital.commons.utils.AmqpUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import java.util.HashMap;

import static ke.co.dynamodigital.commons.utils.AmqpUtils.RETRIES_HEADER;
import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;

/**
 * @author Bibibiu
 * created 9/7/19 at 02:24
 **/
@Slf4j
@EnableBinding({
        ParkingSource.class,
        ParkingSink.class
})
public class ListenersParking {

    @Autowired
    private MessageSender messageSender;

    /**
     * <p>Delayed exchange default queue destination that sends the message to provided
     * destination in the header. This queue expects any generic message logs the
     * events and return the message to the provided return address</p>
     * <p><strong>Note:</strong> Message payload must be serialized to jason before sending
     * it to this queue</p>
     * @param messageObject Delayed message object
     * @param retries Required header. Number of times a message has been delayed
     * @param delay Required header. Milliseconds message has been delayed
     * @param address Required header. Name of channel to return the delayed message
     */
    @StreamListener(target = ParkingSink.INPUT)
    public void park(
            Message <?> messageObject,
            @Header(name = RETRIES_HEADER) Integer retries,
            @Header(name = AmqpUtils.DELAY_HEADER) Integer delay,
            @Header(name = AmqpUtils.RETURN_HEADER) String address
    ) {

        log.trace("Parked message: {}",writeJson(messageObject));
        log.info("\n====================================================================================================" +
                "\nParkedMessage: {}" +
                "\nDuration: {}" +
                "\nParkedTimes: {}" +
                "\nReturnAddress: {}" +
                "\n===================================================================================================="
                ,writeJson(messageObject.getPayload()),delay,retries,address);

        val headers = new HashMap<String,Object>(messageObject.getHeaders()){{
            put(RETRIES_HEADER, retries + 1);
        }};

        Message<Object> message = AmqpUtils.buildMessageFrom(messageObject.getPayload(),headers);

        messageSender.send(message,address);
    }
}
