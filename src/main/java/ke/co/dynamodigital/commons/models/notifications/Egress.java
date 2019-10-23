package ke.co.dynamodigital.commons.models.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Bibibiu
 * created 10/19/19 at 23:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Egress {
    EgressType type;
    List<Recipient> recipients;
}
