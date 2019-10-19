package ke.co.dynamodigital.commons.models.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Bibibiu
 * created 9/2/19 at 23:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Contact {
    private String phoneNumber;
    private String email;
    private String slackWebHook;
    private String callbackUrl;
}
