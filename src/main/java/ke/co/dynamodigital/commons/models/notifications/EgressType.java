package ke.co.dynamodigital.commons.models.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bibibiu
 * created 9/2/19 at 22:36
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EgressType {
    @Builder.Default
    private Route route = Route.SMS;
    @Builder.Default
    private SmsGateway smsGateway = SmsGateway.AFRICAS_TALKING;
    @Builder.Default
    private WhatsappGateway whatsappGateway = WhatsappGateway.CLICKATELL;
}
