package ke.co.dynamodigital.commons.models.notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.List;

/**
 * @author Bibibiu
 * created 9/2/19 at 23:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class TokenNotification extends Notification {
    private String token;

    private String units;

    private String tariff;

    private String amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Nairobi")
    private String timestamp;

    private String meterNumber;

}
