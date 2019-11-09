package ke.co.dynamodigital.commons.stream.parking;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Bibibiu
 * created 9/7/19 at 02:19
 **/
public interface ParkingSink {

    String INPUT = "parkingSinkInput";

    @Input(ParkingSink.INPUT)
    SubscribableChannel processorInput();
}
