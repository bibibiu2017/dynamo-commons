package ke.co.dynamodigital.commons.models.notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import ke.co.dynamodigital.commons.models.notifications.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 10/20/19 at 05:51
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenNotification {
    private String token;
    private String units;
    private String tariff;
    private String amount;
    private String meterNumber;
    private String refNumber;
    private MessageType messageType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Nairobi")
    private String timestamp;
}
