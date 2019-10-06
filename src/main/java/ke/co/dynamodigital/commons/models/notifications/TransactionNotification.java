package ke.co.dynamodigital.commons.models.notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Map;

/**
 * @author Bibibiu
 * created 9/4/19 at 08:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class TransactionNotification extends Notification {
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Nairobi")
    private String timestamp;

    private String ref;

    private String amount;

    private String message;

    private Map<String, String> additionalInfo;

}
