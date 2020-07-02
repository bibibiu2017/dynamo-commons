package ke.co.dynamodigital.commons.models.notifications;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * @author Bibibiu
 * created 9/2/19 at 21:08
 * <strong>Note:</strong> For every egress type provided all recepients must have the required
 * egress address
 * a valid address
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Notification {
    private NotificationType notificationType;
    private Egress egress;
    @Singular("requirement")
    private Map<String, Object> requirements;
}
