package ke.co.dynamodigital.commons.models.notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import ke.co.dynamodigital.commons.models.notifications.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 10/20/19 at 05:52
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionNotification {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Nairobi")
    private String timestamp;
    private String status;
    private String refNumber;
    private String amount;
    private String message;
    private MessageType messageType;
}
