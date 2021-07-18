package ke.co.dynamodigital.commons.testutils;

import com.fasterxml.jackson.annotation.JsonFormat;
import ke.co.dynamodigital.commons.models.notification.MessageType;
import ke.co.dynamodigital.commons.models.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

/**
 * @author Bibibiu
 * created 10/20/19 at 01:56
 **/
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TokenNotification extends Notification {
    private String token;
    private String units;
    private String tariff;
    private String amount;
    private String meterNumber;
    private String refNumber;
    private MessageType messageType;
    @Column(name = "timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Nairobi")
    private String timestamp;
}
