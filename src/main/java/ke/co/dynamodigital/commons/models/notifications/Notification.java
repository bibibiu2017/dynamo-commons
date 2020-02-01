package ke.co.dynamodigital.commons.models.notifications;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.List;
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
    @Singular("egress")
    private List<Egress> egresses;
    @Singular("requirement")
    private Map<String, String> requirements;
}
