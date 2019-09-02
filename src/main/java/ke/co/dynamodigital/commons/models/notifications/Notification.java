package ke.co.dynamodigital.commons.models.notifications;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class Notification {
    List<EgressType> routes;
    List<Recipient> recipients;
}
