package ke.co.dynamodigital.commons.stream.parking;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Bibibiu
 * created 9/7/19 at 02:16
 **/
public interface ParkingSource {

    String OUTPUT = "parkingSourceOutput";

    @Output(ParkingSource.OUTPUT)
    MessageChannel sourceOutput();
}
